package com.zzyl.ai.controller;

import com.zzyl.ai.dto.AiChatSendRequest;
import com.zzyl.ai.dto.AiChatStopRequest;
import com.zzyl.ai.service.IAiConversationService;
import com.zzyl.ai.vo.AiConversationDetailVo;
import com.zzyl.ai.vo.AiConversationSummaryVo;
import com.zzyl.ai.vo.AiWorkflowRunResponse;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.exception.ServiceException;
import com.zzyl.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * AI 机器人前端接口。
 */
@Api(tags = "AI 机器人接口")
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiConversationController extends BaseController
{
    private final IAiConversationService aiConversationService;

    @ApiOperation("创建 AI 会话")
    @PostMapping("/conversation/create")
    public AjaxResult createConversation()
    {
        String conversationId = aiConversationService.createConversation(getUserId(), getUsername());
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("id", conversationId);
        return AjaxResult.success("创建会话成功", data);
    }

    @ApiOperation("查询 AI 会话历史")
    @GetMapping("/conversation/list")
    public AjaxResult listConversations()
    {
        List<AiConversationSummaryVo> data = aiConversationService.listConversations(getUserId());
        return AjaxResult.success(data);
    }

    @ApiOperation("查询 AI 会话详情")
    @GetMapping("/conversation/{conversationId}")
    public AjaxResult getConversation(@PathVariable("conversationId") @ApiParam("本地会话 ID") String conversationId)
    {
        if (StringUtils.isBlank(conversationId))
        {
            throw new ServiceException("conversationId 不能为空");
        }
        AiConversationDetailVo data = aiConversationService.getConversationDetail(getUserId(), conversationId);
        return AjaxResult.success(data);
    }

    @ApiOperation("发送消息到 AI")
    @PostMapping("/chat/send")
    public AjaxResult sendMessage(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization,
                                  @RequestBody @ApiParam("AI 发送消息请求") AiChatSendRequest request)
    {
        String conversationId = resolveConversationId(request);
        if (StringUtils.isBlank(conversationId))
        {
            throw new ServiceException("conversationId/sessionId 不能为空");
        }
        if (StringUtils.isBlank(request.getContent()))
        {
            throw new ServiceException("content 不能为空");
        }

        AiWorkflowRunResponse response = aiConversationService.sendMessage(getUserId(),
                conversationId, request.getContent(), authorization);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("conversationId", conversationId);
        data.put("sessionId", conversationId);
        data.put("reply", response.getAnswer());
        data.put("taskId", response.getTaskId());
        data.put("workflowRunId", response.getWorkflowRunId());
        data.put("status", response.getStatus());
        data.put("outputs", response.getOutputs());
        return AjaxResult.success(StringUtils.isBlank(response.getAnswer()) ? "发送成功" : response.getAnswer(), data);
    }

    @ApiOperation("流式发送消息到 AI")
    @PostMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamMessage(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization,
                                    @RequestBody @ApiParam("AI 发送消息请求") AiChatSendRequest request)
    {
        String conversationId = resolveConversationId(request);
        if (StringUtils.isBlank(conversationId))
        {
            throw new ServiceException("conversationId/sessionId 不能为空");
        }
        if (StringUtils.isBlank(request.getContent()))
        {
            throw new ServiceException("content 不能为空");
        }

        SseEmitter emitter = new SseEmitter(0L);
        aiConversationService.streamMessage(getUserId(), conversationId, request.getContent(), authorization, emitter);
        return emitter;
    }

    @ApiOperation("停止 AI 生成任务")
    @PostMapping("/chat/stop")
    public AjaxResult stopMessage(@RequestBody @ApiParam("AI 停止生成请求") AiChatStopRequest request)
    {
        String conversationId = resolveConversationId(request);
        if (StringUtils.isBlank(conversationId))
        {
            throw new ServiceException("conversationId/sessionId 不能为空");
        }
        aiConversationService.stopMessage(getUserId(), conversationId, request.getTaskId());
        return AjaxResult.success("停止生成成功");
    }

    @ApiOperation("删除 AI 会话")
    @DeleteMapping("/conversation/{conversationId}")
    public AjaxResult deleteConversation(@PathVariable("conversationId") @ApiParam("本地会话 ID") String conversationId)
    {
        if (StringUtils.isBlank(conversationId))
        {
            throw new ServiceException("conversationId 不能为空");
        }
        aiConversationService.deleteConversation(getUserId(), conversationId);
        return AjaxResult.success("删除会话成功");
    }

    private String resolveConversationId(AiChatSendRequest request)
    {
        if (request == null)
        {
            return null;
        }
        return StringUtils.isNotBlank(request.getConversationId()) ? request.getConversationId() : request.getSessionId();
    }

    private String resolveConversationId(AiChatStopRequest request)
    {
        if (request == null)
        {
            return null;
        }
        return StringUtils.isNotBlank(request.getConversationId()) ? request.getConversationId() : request.getSessionId();
    }
}
