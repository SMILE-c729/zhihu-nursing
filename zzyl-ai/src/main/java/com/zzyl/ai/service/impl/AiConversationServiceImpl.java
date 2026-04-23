package com.zzyl.ai.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zzyl.ai.config.AiConversationProperties;
import com.zzyl.ai.config.AiProperties;
import com.zzyl.ai.constant.AiConstants;
import com.zzyl.ai.domain.AiConversationMessage;
import com.zzyl.ai.domain.AiConversationSession;
import com.zzyl.ai.service.IAiConversationService;
import com.zzyl.ai.vo.AiConversationDetailVo;
import com.zzyl.ai.vo.AiConversationSummaryVo;
import com.zzyl.ai.vo.AiWorkflowRunResponse;
import com.zzyl.common.core.redis.RedisCache;
import com.zzyl.common.exception.ServiceException;
import com.zzyl.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * AI 会话服务实现。
 */
@Service
public class AiConversationServiceImpl implements IAiConversationService {
    private static final String WORKFLOW_RUN_PATH = "/workflows/run";

    private static final String WORKFLOW_STOP_PATH_PREFIX = "/workflows/tasks/";

    private static final String WORKFLOW_STOP_PATH_SUFFIX = "/stop";

    private static final String DEFAULT_OUTPUT_KEY = "text";

    private static final String DIFY_PING_EVENT = "event: ping";

    private final RedisCache redisCache;

    private final AiProperties aiProperties;

    private final AiConversationProperties aiConversationProperties;

    public AiConversationServiceImpl(RedisCache redisCache, AiProperties aiProperties,
                                     AiConversationProperties aiConversationProperties) {
        this.redisCache = redisCache;
        this.aiProperties = aiProperties;
        this.aiConversationProperties = aiConversationProperties;
    }

    @Override
    public String createConversation(Long userId, String username) {
        String conversationId = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();

        AiConversationSession session = new AiConversationSession();
        session.setLocalConversationId(conversationId);
        session.setUserId(userId);
        session.setUsername(username);
        session.setDifyUser(AiConstants.DIFY_USER_PREFIX + userId);
        session.setTitle("新会话");
        session.setStatus(AiConstants.STATUS_NEW);
        session.setCreateTime(now);
        session.setUpdateTime(now);

        cacheSessionAndIndex(session);
        return conversationId;
    }

    @Override
    public List<AiConversationSummaryVo> listConversations(Long userId) {
        List<String> conversationIds = getUserConversationIds(userId);
        if (conversationIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> liveConversationIds = new ArrayList<>();
        List<AiConversationSummaryVo> result = new ArrayList<>();
        for (String conversationId : conversationIds) {
            AiConversationSession session = getSession(conversationId);
            if (session == null || !Objects.equals(userId, session.getUserId())) {
                continue;
            }
            liveConversationIds.add(conversationId);
            result.add(toSummaryVo(session));
        }

        if (liveConversationIds.size() != conversationIds.size()) {
            cacheUserConversationIds(userId, liveConversationIds);
        }
        return result;
    }

    @Override
    public AiConversationDetailVo getConversationDetail(Long userId, String conversationId) {
        return toDetailVo(getRequiredSession(userId, conversationId));
    }

    @Override
    public AiWorkflowRunResponse sendMessage(Long userId, String conversationId, String content,
                                             String authorizationHeader) {
        if (StringUtils.isBlank(authorizationHeader)) {
            throw new ServiceException("缺少 Authorization 请求头，无法传递给 Dify workflow");
        }

        AiConversationSession session = getRequiredSession(userId, conversationId);
        prepareUserMessage(session, content);
        cacheSessionAndIndex(session);

        try {
            AiWorkflowRunResponse response = runWorkflow(session, content, authorizationHeader);
            appendMessage(session, AiConstants.ROLE_ASSISTANT, response.getAnswer(), response.getTaskId(),
                    AiConstants.STATUS_COMPLETED);
            session.setWorkflowRunId(response.getWorkflowRunId());
            session.setCurrentTaskId(null);
            session.setStatus(AiConstants.STATUS_COMPLETED);
            session.setUpdateTime(new Date());
            cacheSessionAndIndex(session);
            return response;
        } catch (ServiceException e) {
            markSessionError(session, e.getMessage(), null);
            throw e;
        }
    }

    @Override
    public void streamMessage(Long userId, String conversationId, String content, String authorizationHeader,
                              SseEmitter emitter) {
        if (StringUtils.isBlank(authorizationHeader)) {
            throw new ServiceException("缺少 Authorization 请求头，无法传递给 Dify workflow");
        }

        AiConversationSession session = getRequiredSession(userId, conversationId);
        prepareUserMessage(session, content);
        cacheSessionAndIndex(session);

        CompletableFuture.runAsync(() -> executeStreamMessage(userId, conversationId, content, authorizationHeader, emitter));
    }

    @Override
    public void stopMessage(Long userId, String conversationId, String taskId) {
        AiConversationSession session = getRequiredSession(userId, conversationId);
        String effectiveTaskId = StringUtils.isNotBlank(taskId) ? taskId : session.getCurrentTaskId();
        if (StringUtils.isBlank(effectiveTaskId)) {
            throw new ServiceException("当前没有正在生成的任务");
        }

        stopWorkflowTask(session, effectiveTaskId);

        AiConversationSession latestSession = getRequiredSession(userId, conversationId);
        latestSession.setStatus(AiConstants.STATUS_STOPPED);
        latestSession.setCurrentTaskId(null);
        latestSession.setUpdateTime(new Date());
        cacheSessionAndIndex(latestSession);
    }

    @Override
    public void deleteConversation(Long userId, String conversationId) {
        AiConversationSession session = getSession(conversationId);
        if (session == null) {
            removeConversationFromUserIndex(userId, conversationId);
            return;
        }

        validateOwner(userId, session);
        redisCache.deleteObject(buildCacheKey(conversationId));
        removeConversationFromUserIndex(userId, conversationId);
    }

    private void executeStreamMessage(Long userId, String conversationId, String query,
                                      String authorizationHeader, SseEmitter emitter) {
        StringBuilder answerBuilder = new StringBuilder();
        AiWorkflowRunResponse workflowResponse = new AiWorkflowRunResponse();
        boolean workflowFinished = false;

        try {
            AiConversationSession session = getRequiredSession(userId, conversationId);
            sendEvent(emitter, "heartbeat", buildEventData(conversationId, null, null, null,
                    AiConstants.STATUS_RUNNING));
            String requestBody = buildWorkflowRequestBody(session, query, authorizationHeader, "streaming");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(buildWorkflowRunUrl()))
                    .timeout(Duration.ofMillis(resolveReadTimeout()))
                    .header("Authorization", buildDifyAuthorizationHeader())
                    .header("Content-Type", "application/json; charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(resolveConnectTimeout()))
                    .build();
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new ServiceException("Dify workflow 请求失败，HTTP状态码: " + response.statusCode()
                        + "，响应: " + readResponseBody(response.body()));
            }

            workflowFinished = readDifyStream(userId, conversationId, response.body(), answerBuilder,
                    workflowResponse, emitter);
            finishStreamSession(userId, conversationId, answerBuilder.toString(), workflowResponse, workflowFinished, emitter);
        } catch (IOException e) {
            finishStreamError(userId, conversationId, answerBuilder.toString(), workflowResponse.getTaskId(),
                    "连接 Dify workflow 失败: " + e.getMessage(), emitter);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            finishStreamError(userId, conversationId, answerBuilder.toString(), workflowResponse.getTaskId(),
                    "调用 Dify workflow 被中断", emitter);
        } catch (IllegalArgumentException e) {
            finishStreamError(userId, conversationId, answerBuilder.toString(), workflowResponse.getTaskId(),
                    "Dify workflow 地址配置错误: " + e.getMessage(), emitter);
        } catch (ServiceException e) {
            finishStreamError(userId, conversationId, answerBuilder.toString(), workflowResponse.getTaskId(),
                    e.getMessage(), emitter);
        } catch (RuntimeException e) {
            finishStreamError(userId, conversationId, answerBuilder.toString(), workflowResponse.getTaskId(),
                    "AI 流式响应处理失败: " + e.getMessage(), emitter);
        }
    }

    private boolean readDifyStream(Long userId, String conversationId, InputStream inputStream,
                                   StringBuilder answerBuilder, AiWorkflowRunResponse workflowResponse,
                                   SseEmitter emitter) throws IOException {
        boolean workflowFinished = false;
        StringBuilder eventBuffer = new StringBuilder();
        long streamIdleTimeout = resolveReadTimeout();
        long lastEventTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                long now = System.currentTimeMillis();
                if (now - lastEventTime > streamIdleTimeout) {
                    throw new ServiceException("Dify workflow 流式响应超时");
                }
                lastEventTime = now;

                if (DIFY_PING_EVENT.equals(line)) {
                    sendEventQuietly(emitter, "heartbeat", buildEventData(conversationId,
                            workflowResponse.getTaskId(), workflowResponse.getWorkflowRunId(), null,
                            AiConstants.STATUS_RUNNING));
                    continue;
                }

                if (StringUtils.isBlank(line)) {
                    if (eventBuffer.length() > 0) {
                        workflowFinished = handleDifyStreamPayload(userId, conversationId, eventBuffer.toString(),
                                answerBuilder, workflowResponse, emitter);
                        eventBuffer.setLength(0);
                    }
                    if (workflowFinished) {
                        break;
                    }
                    continue;
                }

                if (line.startsWith("data:")) {
                    eventBuffer.append(line.substring(5).trim());
                }
            }
        }

        if (!workflowFinished && eventBuffer.length() > 0) {
            workflowFinished = handleDifyStreamPayload(userId, conversationId, eventBuffer.toString(),
                    answerBuilder, workflowResponse, emitter);
        }
        return workflowFinished;
    }

    private boolean handleDifyStreamPayload(Long userId, String conversationId, String payload,
                                            StringBuilder answerBuilder, AiWorkflowRunResponse workflowResponse,
                                            SseEmitter emitter) throws IOException {
        if (StringUtils.isBlank(payload) || "[DONE]".equals(payload)) {
            return false;
        }

        JSONObject root = JSON.parseObject(payload);
        String event = root.getString("event");
        String taskId = root.getString("task_id");
        String workflowRunId = root.getString("workflow_run_id");

        if (StringUtils.isNotBlank(taskId) && StringUtils.isBlank(workflowResponse.getTaskId())) {
            workflowResponse.setTaskId(taskId);
        }
        if (StringUtils.isNotBlank(workflowRunId)) {
            workflowResponse.setWorkflowRunId(workflowRunId);
        }

        if ("workflow_started".equals(event)) {
            AiConversationSession session = getRequiredSession(userId, conversationId);
            session.setCurrentTaskId(taskId);
            session.setWorkflowRunId(workflowRunId);
            session.setStatus(AiConstants.STATUS_RUNNING);
            session.setUpdateTime(new Date());
            cacheSessionAndIndex(session);
            sendEvent(emitter, "task", buildEventData(conversationId, taskId, workflowRunId, null, null));
            return false;
        }

        if ("text_chunk".equals(event)) {
            String delta = resolveStreamText(root);
            if (StringUtils.isNotBlank(delta)) {
                answerBuilder.append(delta);
                sendEvent(emitter, "delta", buildEventData(conversationId, taskId, workflowRunId, delta, null));
            }
            return false;
        }

        if ("workflow_finished".equals(event)) {
            JSONObject data = root.getJSONObject("data");
            if (data == null) {
                throw new ServiceException("Dify workflow 响应缺少 data: " + payload);
            }

            workflowResponse.setStatus(data.getString("status"));
            workflowResponse.setError(data.getString("error"));

            JSONObject outputs = data.getJSONObject("outputs");
            if (outputs != null) {
                workflowResponse.setOutputs(outputs);
                workflowResponse.setAnswer(resolveAnswer(outputs));
                if (answerBuilder.length() == 0 && StringUtils.isNotBlank(workflowResponse.getAnswer())) {
                    answerBuilder.append(workflowResponse.getAnswer());
                    sendEvent(emitter, "delta", buildEventData(conversationId, taskId, workflowRunId,
                            workflowResponse.getAnswer(), null));
                }
            }

            AiConversationSession latestSession = getRequiredSession(userId, conversationId);
            if (!AiConstants.STATUS_STOPPED.equals(latestSession.getStatus())
                    && !StringUtils.equals("succeeded", workflowResponse.getStatus())) {
                throw new ServiceException("Dify workflow 执行失败: "
                        + (StringUtils.isBlank(workflowResponse.getError())
                        ? workflowResponse.getStatus() : workflowResponse.getError()));
            }
            return true;
        }

        if ("error".equals(event)) {
            throw new ServiceException(StringUtils.defaultIfBlank(root.getString("message"), "Dify workflow 返回错误事件"));
        }

        return false;
    }

    private void finishStreamSession(Long userId, String conversationId, String answer,
                                     AiWorkflowRunResponse workflowResponse, boolean workflowFinished,
                                     SseEmitter emitter) throws IOException {
        AiConversationSession session = getRequiredSession(userId, conversationId);
        boolean stopped = AiConstants.STATUS_STOPPED.equals(session.getStatus());
        String finalStatus = stopped ? AiConstants.STATUS_STOPPED : AiConstants.STATUS_COMPLETED;
        if (!workflowFinished && !stopped) {
            throw new ServiceException("Dify workflow 流式响应提前结束");
        }

        appendMessage(session, AiConstants.ROLE_ASSISTANT,
                StringUtils.defaultIfBlank(answer, stopped ? "回答已停止。" : "（AI 助手没有返回内容）"),
                workflowResponse.getTaskId(), finalStatus);
        session.setWorkflowRunId(workflowResponse.getWorkflowRunId());
        session.setCurrentTaskId(null);
        session.setStatus(finalStatus);
        session.setUpdateTime(new Date());
        cacheSessionAndIndex(session);

        Map<String, Object> doneData = buildEventData(conversationId, workflowResponse.getTaskId(),
                workflowResponse.getWorkflowRunId(), null, finalStatus);
        doneData.put("reply", answer);
        doneData.put("outputs", workflowResponse.getOutputs());
        sendEvent(emitter, "done", doneData);
        emitter.complete();
    }

    private void finishStreamError(Long userId, String conversationId, String answer, String taskId,
                                   String message, SseEmitter emitter) {
        AiConversationSession session = getSession(conversationId);
        if (session != null && Objects.equals(userId, session.getUserId())) {
            if (AiConstants.STATUS_STOPPED.equals(session.getStatus())) {
                appendMessage(session, AiConstants.ROLE_ASSISTANT,
                        StringUtils.defaultIfBlank(answer, "回答已停止。"), taskId, AiConstants.STATUS_STOPPED);
                session.setCurrentTaskId(null);
                session.setUpdateTime(new Date());
                cacheSessionAndIndex(session);
                sendEventQuietly(emitter, "done", buildEventData(conversationId, taskId,
                        session.getWorkflowRunId(), null, AiConstants.STATUS_STOPPED));
                emitter.complete();
                return;
            }

            markSessionError(session, StringUtils.defaultIfBlank(answer, message), taskId);
        }

        Map<String, Object> data = buildEventData(conversationId, taskId, null, null, AiConstants.STATUS_ERROR);
        data.put("message", message);
        sendEventQuietly(emitter, "error", data);
        emitter.complete();
    }

    private AiWorkflowRunResponse runWorkflow(AiConversationSession session, String query, String authorizationHeader) {
        String requestBody = buildWorkflowRequestBody(session, query, authorizationHeader, "blocking");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildWorkflowRunUrl()))
                .timeout(Duration.ofMillis(resolveReadTimeout()))
                .header("Authorization", buildDifyAuthorizationHeader())
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(resolveConnectTimeout()))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parseWorkflowResponse(response);
        } catch (IOException e) {
            throw new ServiceException("连接 Dify workflow 失败: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException("调用 Dify workflow 被中断");
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Dify workflow 地址配置错误: " + e.getMessage());
        }
    }

    private void stopWorkflowTask(AiConversationSession session, String taskId) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("user", session.getDifyUser());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildWorkflowStopUrl(taskId)))
                .timeout(Duration.ofMillis(resolveReadTimeout()))
                .header("Authorization", buildDifyAuthorizationHeader())
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(body)))
                .build();

        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(resolveConnectTimeout()))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new ServiceException("停止 Dify workflow 失败，HTTP状态码: " + response.statusCode()
                        + "，响应: " + response.body());
            }
        } catch (IOException e) {
            throw new ServiceException("连接 Dify workflow 停止接口失败: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException("调用 Dify workflow 停止接口被中断");
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Dify workflow 停止接口地址配置错误: " + e.getMessage());
        }
    }

    private String buildWorkflowRequestBody(AiConversationSession session, String query, String authorizationHeader,
                                            String responseMode) {
        String historyContext = buildHistoryContext(session, query);
        String contextualQuery = buildContextualQuery(historyContext, query);

        Map<String, Object> inputs = new LinkedHashMap<>();
        inputs.put("query", contextualQuery);
        inputs.put("currentQuestion", query);
        inputs.put("history", historyContext);
        inputs.put("authorization", authorizationHeader);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("inputs", inputs);
        body.put("response_mode", responseMode);
        body.put("user", session.getDifyUser());
        return JSON.toJSONString(body);
    }

    private String buildContextualQuery(String historyContext, String query) {
        if (StringUtils.isBlank(historyContext)) {
            return query;
        }
        return "请结合以下历史对话继续回答用户当前问题。"
                + "\n\n【历史对话】\n" + historyContext
                + "\n\n【当前问题】\n" + query;
    }

    private String buildHistoryContext(AiConversationSession session, String currentQuestion) {
        List<AiConversationMessage> messages = getMessages(session);
        if (messages.isEmpty()) {
            return "";
        }

        int endExclusive = messages.size();
        AiConversationMessage latestMessage = messages.get(messages.size() - 1);
        if (AiConstants.ROLE_USER.equals(latestMessage.getRole())
                && StringUtils.equals(latestMessage.getContent(), currentQuestion)) {
            endExclusive = messages.size() - 1;
        }

        int start = Math.max(0, endExclusive - resolveMaxContextMessageSize());
        int remainingContextChars = resolveMaxContextTotalChars();
        StringBuilder context = new StringBuilder();
        for (int i = start; i < endExclusive; i++) {
            AiConversationMessage message = messages.get(i);
            if (StringUtils.isBlank(message.getContent())) {
                continue;
            }
            String compactContent = compactContextContent(message.getContent(), resolveMaxContextMessageChars());
            if (StringUtils.isBlank(compactContent) || remainingContextChars <= 0) {
                break;
            }
            if (compactContent.length() > remainingContextChars) {
                compactContent = compactContent.substring(0, remainingContextChars);
            }
            context.append(AiConstants.ROLE_USER.equals(message.getRole()) ? "用户：" : "助手：")
                    .append(compactContent)
                    .append('\n');
            remainingContextChars = resolveMaxContextTotalChars() - context.length();
        }
        return context.toString().trim();
    }

    private String compactContextContent(String content, int maxChars) {
        String normalized = StringUtils.defaultString(content).replaceAll("\\s+", " ").trim();
        if (maxChars <= 0 || normalized.length() <= maxChars) {
            return normalized;
        }
        return normalized.substring(0, maxChars) + "...";
    }

    private AiWorkflowRunResponse parseWorkflowResponse(HttpResponse<String> httpResponse) {
        String body = httpResponse.body();
        if (httpResponse.statusCode() < 200 || httpResponse.statusCode() >= 300) {
            throw new ServiceException("Dify workflow 请求失败，HTTP状态码: " + httpResponse.statusCode()
                    + "，响应: " + body);
        }

        JSONObject root = JSON.parseObject(body);
        JSONObject data = root.getJSONObject("data");
        if (data == null) {
            throw new ServiceException("Dify workflow 响应缺少 data: " + body);
        }

        AiWorkflowRunResponse result = new AiWorkflowRunResponse();
        result.setTaskId(root.getString("task_id"));
        result.setWorkflowRunId(root.getString("workflow_run_id"));
        result.setStatus(data.getString("status"));
        result.setError(data.getString("error"));

        JSONObject outputs = data.getJSONObject("outputs");
        if (outputs != null) {
            result.setOutputs(outputs);
            result.setAnswer(resolveAnswer(outputs));
        }

        if (!StringUtils.equals("succeeded", result.getStatus())) {
            throw new ServiceException("Dify workflow 执行失败: "
                    + (StringUtils.isBlank(result.getError()) ? result.getStatus() : result.getError()));
        }

        return result;
    }

    private String resolveStreamText(JSONObject root) {
        String text = root.getString("text");
        if (StringUtils.isNotBlank(text)) {
            return text;
        }

        JSONObject data = root.getJSONObject("data");
        if (data == null) {
            return null;
        }
        return data.getString("text");
    }

    private String resolveAnswer(JSONObject outputs) {
        String answer = outputs.getString(DEFAULT_OUTPUT_KEY);
        if (StringUtils.isNotBlank(answer)) {
            return answer;
        }
        answer = outputs.getString("answer");
        if (StringUtils.isNotBlank(answer)) {
            return answer;
        }
        answer = outputs.getString("result");
        if (StringUtils.isNotBlank(answer)) {
            return answer;
        }
        return outputs.toJSONString();
    }

    private void prepareUserMessage(AiConversationSession session, String content) {
        appendMessage(session, AiConstants.ROLE_USER, content, null, AiConstants.STATUS_COMPLETED);
        if ("新会话".equals(session.getTitle()) || StringUtils.isBlank(session.getTitle())) {
            session.setTitle(buildConversationTitle(content));
        }
        session.setStatus(AiConstants.STATUS_RUNNING);
        session.setCurrentTaskId(null);
        session.setUpdateTime(new Date());
    }

    private void appendMessage(AiConversationSession session, String role, String content, String taskId, String status) {
        AiConversationMessage message = new AiConversationMessage();
        message.setId(UUID.randomUUID().toString().replace("-", ""));
        message.setRole(role);
        message.setContent(content);
        message.setTaskId(taskId);
        message.setStatus(status);
        message.setCreateTime(new Date());
        List<AiConversationMessage> messages = getMessages(session);
        messages.add(message);
        trimSessionMessages(messages);
    }

    private void trimSessionMessages(List<AiConversationMessage> messages) {
        int maxSessionMessageSize = resolveMaxSessionMessageSize();
        if (maxSessionMessageSize <= 0 || messages.size() <= maxSessionMessageSize) {
            return;
        }
        messages.subList(0, messages.size() - maxSessionMessageSize).clear();
    }

    private void markSessionError(AiConversationSession session, String message, String taskId) {
        appendMessage(session, AiConstants.ROLE_ASSISTANT,
                StringUtils.defaultIfBlank(message, "抱歉，AI 服务出现异常，请稍后重试。"),
                taskId, AiConstants.STATUS_ERROR);
        session.setCurrentTaskId(null);
        session.setStatus(AiConstants.STATUS_ERROR);
        session.setUpdateTime(new Date());
        cacheSessionAndIndex(session);
    }

    private List<AiConversationMessage> getMessages(AiConversationSession session) {
        if (session.getMessages() == null) {
            session.setMessages(new ArrayList<>());
        }
        return session.getMessages();
    }

    private AiConversationSummaryVo toSummaryVo(AiConversationSession session) {
        AiConversationSummaryVo vo = new AiConversationSummaryVo();
        vo.setConversationId(session.getLocalConversationId());
        vo.setTitle(StringUtils.defaultIfBlank(session.getTitle(), "新会话"));
        vo.setStatus(StringUtils.defaultIfBlank(session.getStatus(), AiConstants.STATUS_NEW));
        vo.setUpdateTime(session.getUpdateTime());
        vo.setMessageCount(getMessages(session).size());
        vo.setLastMessage(resolveLastMessage(session));
        return vo;
    }

    private AiConversationDetailVo toDetailVo(AiConversationSession session) {
        AiConversationDetailVo vo = new AiConversationDetailVo();
        vo.setConversationId(session.getLocalConversationId());
        vo.setTitle(StringUtils.defaultIfBlank(session.getTitle(), "新会话"));
        vo.setStatus(StringUtils.defaultIfBlank(session.getStatus(), AiConstants.STATUS_NEW));
        vo.setCurrentTaskId(session.getCurrentTaskId());
        vo.setWorkflowRunId(session.getWorkflowRunId());
        vo.setCreateTime(session.getCreateTime());
        vo.setUpdateTime(session.getUpdateTime());
        vo.setMessages(getMessages(session));
        return vo;
    }

    private String resolveLastMessage(AiConversationSession session) {
        List<AiConversationMessage> messages = getMessages(session);
        if (messages.isEmpty()) {
            return "";
        }
        for (int i = messages.size() - 1; i >= 0; i--) {
            String content = messages.get(i).getContent();
            if (StringUtils.isNotBlank(content)) {
                return content.length() > 36 ? content.substring(0, 36) + "..." : content;
            }
        }
        return "";
    }

    private String buildConversationTitle(String content) {
        String normalized = StringUtils.defaultString(content).replaceAll("\\s+", " ").trim();
        if (normalized.length() <= 24) {
            return normalized;
        }
        return normalized.substring(0, 24) + "...";
    }

    private Map<String, Object> buildEventData(String conversationId, String taskId, String workflowRunId,
                                               String content, String status) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("conversationId", conversationId);
        data.put("taskId", taskId);
        data.put("workflowRunId", workflowRunId);
        data.put("content", content);
        data.put("status", status);
        return data;
    }

    private void sendEvent(SseEmitter emitter, String eventName, Object data) throws IOException {
        emitter.send(SseEmitter.event().name(eventName).data(data));
    }

    private void sendEventQuietly(SseEmitter emitter, String eventName, Object data) {
        try {
            sendEvent(emitter, eventName, data);
        } catch (IOException ignored) {
        }
    }

    private String readResponseBody(InputStream inputStream) throws IOException {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }
        return body.toString();
    }

    private String buildWorkflowRunUrl() {
        return buildDifyApiBaseUrl() + WORKFLOW_RUN_PATH;
    }

    private String buildWorkflowStopUrl(String taskId) {
        return buildDifyApiBaseUrl() + WORKFLOW_STOP_PATH_PREFIX + taskId + WORKFLOW_STOP_PATH_SUFFIX;
    }

    private String buildDifyApiBaseUrl() {
        String baseUrl = aiProperties.getBaseUrl();
        if (StringUtils.isBlank(baseUrl)) {
            throw new ServiceException("Dify baseUrl 未配置");
        }
        String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        if (normalizedBaseUrl.endsWith(WORKFLOW_RUN_PATH)) {
            return normalizedBaseUrl.substring(0, normalizedBaseUrl.length() - WORKFLOW_RUN_PATH.length());
        }
        return normalizedBaseUrl;
    }

    private String buildDifyAuthorizationHeader() {
        String apiKey = aiProperties.getApiKey();
        if (StringUtils.isBlank(apiKey)) {
            throw new ServiceException("Dify apiKey 未配置");
        }
        if (StringUtils.startsWithIgnoreCase(apiKey, AiConstants.AUTHORIZATION_PREFIX)) {
            return apiKey;
        }
        return AiConstants.AUTHORIZATION_PREFIX + apiKey;
    }

    private long resolveConnectTimeout() {
        Integer connectTimeout = aiProperties.getConnectTimeout();
        return connectTimeout == null || connectTimeout <= 0 ? 5000L : connectTimeout.longValue();
    }

    private long resolveReadTimeout() {
        Integer readTimeout = aiProperties.getReadTimeout();
        return readTimeout == null || readTimeout <= 0 ? 60000L : readTimeout.longValue();
    }

    private AiConversationSession getRequiredSession(Long userId, String conversationId) {
        AiConversationSession session = getSession(conversationId);
        if (session == null) {
            throw new ServiceException("会话不存在，请先创建会话");
        }
        validateOwner(userId, session);
        return session;
    }

    private AiConversationSession getSession(String conversationId) {
        return redisCache.getCacheObject(buildCacheKey(conversationId));
    }

    private void validateOwner(Long userId, AiConversationSession session) {
        if (!userId.equals(session.getUserId())) {
            throw new ServiceException("无权访问当前会话");
        }
    }

    private void cacheSessionAndIndex(AiConversationSession session) {
        cacheSession(session);
        cacheUserConversationIndex(session);
    }

    private void cacheSession(AiConversationSession session) {
        redisCache.setCacheObject(buildCacheKey(session.getLocalConversationId()), session,
                resolveSessionTtlMinutes(), TimeUnit.MINUTES);
    }

    private void cacheUserConversationIndex(AiConversationSession session) {
        List<String> conversationIds = getUserConversationIds(session.getUserId());
        conversationIds.remove(session.getLocalConversationId());
        conversationIds.add(0, session.getLocalConversationId());
        if (conversationIds.size() > AiConstants.MAX_HISTORY_SIZE) {
            conversationIds = new ArrayList<>(conversationIds.subList(0, AiConstants.MAX_HISTORY_SIZE));
        }
        cacheUserConversationIds(session.getUserId(), conversationIds);
    }

    private List<String> getUserConversationIds(Long userId) {
        List<String> conversationIds = redisCache.getCacheObject(buildUserConversationKey(userId));
        return conversationIds == null ? new ArrayList<>() : new ArrayList<>(conversationIds);
    }

    private void cacheUserConversationIds(Long userId, List<String> conversationIds) {
        redisCache.setCacheObject(buildUserConversationKey(userId), conversationIds,
                resolveSessionTtlMinutes(), TimeUnit.MINUTES);
    }

    private void removeConversationFromUserIndex(Long userId, String conversationId) {
        List<String> conversationIds = getUserConversationIds(userId);
        if (conversationIds.remove(conversationId)) {
            cacheUserConversationIds(userId, conversationIds);
        }
    }

    private Integer resolveSessionTtlMinutes() {
        Integer ttlMinutes = aiConversationProperties.getSessionTtlMinutes();
        return ttlMinutes == null || ttlMinutes <= 0 ? AiConstants.DEFAULT_SESSION_TTL_MINUTES : ttlMinutes;
    }

    private int resolveMaxContextMessageSize() {
        Integer maxContextMessageSize = aiConversationProperties.getMaxContextMessageSize();
        return maxContextMessageSize == null || maxContextMessageSize <= 0
                ? AiConstants.MAX_CONTEXT_MESSAGE_SIZE : maxContextMessageSize;
    }

    private int resolveMaxContextMessageChars() {
        Integer maxContextMessageChars = aiConversationProperties.getMaxContextMessageChars();
        return maxContextMessageChars == null || maxContextMessageChars <= 0
                ? AiConstants.MAX_CONTEXT_MESSAGE_CHARS : maxContextMessageChars;
    }

    private int resolveMaxContextTotalChars() {
        Integer maxContextTotalChars = aiConversationProperties.getMaxContextTotalChars();
        return maxContextTotalChars == null || maxContextTotalChars <= 0
                ? AiConstants.MAX_CONTEXT_TOTAL_CHARS : maxContextTotalChars;
    }

    private int resolveMaxSessionMessageSize() {
        Integer maxSessionMessageSize = aiConversationProperties.getMaxSessionMessageSize();
        return maxSessionMessageSize == null || maxSessionMessageSize <= 0
                ? AiConstants.MAX_SESSION_MESSAGE_SIZE : maxSessionMessageSize;
    }

    private String buildCacheKey(String conversationId) {
        return AiConstants.CONVERSATION_CACHE_PREFIX + conversationId;
    }

    private String buildUserConversationKey(Long userId) {
        return AiConstants.USER_CONVERSATION_CACHE_PREFIX + userId + ":conversations";
    }
}
