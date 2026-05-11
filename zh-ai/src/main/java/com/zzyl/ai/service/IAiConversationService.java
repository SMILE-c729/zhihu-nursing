package com.zzyl.ai.service;

import com.zzyl.ai.vo.AiConversationDetailVo;
import com.zzyl.ai.vo.AiConversationSummaryVo;
import com.zzyl.ai.vo.AiWorkflowRunResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * AI 会话服务接口。
 */
public interface IAiConversationService
{
    /**
     * 创建本地会话。
     *
     * @param userId 当前登录用户 ID
     * @param username 当前登录用户名
     * @return 本地会话 ID
     */
    String createConversation(Long userId, String username);

    /**
     * 查询当前用户的 Redis 临时会话历史。
     *
     * @param userId 当前登录用户 ID
     * @return 会话历史摘要
     */
    List<AiConversationSummaryVo> listConversations(Long userId);

    /**
     * 查询当前用户的指定会话详情。
     *
     * @param userId 当前登录用户 ID
     * @param conversationId 本地会话 ID
     * @return 会话详情
     */
    AiConversationDetailVo getConversationDetail(Long userId, String conversationId);

    /**
     * 向 Dify workflow 发送消息。
     *
     * @param userId 当前登录用户 ID
     * @param conversationId 本地会话 ID
     * @param content 用户输入内容
     * @param authorizationHeader 当前后端请求携带的 Authorization 请求头
     * @return Dify workflow 执行结果
     */
    AiWorkflowRunResponse sendMessage(Long userId, String conversationId, String content, String authorizationHeader);

    /**
     * 向 Dify workflow 发送消息，并以 SSE 方式转发流式结果。
     *
     * @param userId 当前登录用户 ID
     * @param conversationId 本地会话 ID
     * @param content 用户输入内容
     * @param authorizationHeader 当前后端请求携带的 Authorization 请求头
     * @param emitter SSE 输出对象
     */
    void streamMessage(Long userId, String conversationId, String content, String authorizationHeader, SseEmitter emitter);

    /**
     * 停止当前 Dify workflow 生成任务。
     *
     * @param userId 当前登录用户 ID
     * @param conversationId 本地会话 ID
     * @param taskId Dify task_id；为空时使用会话中的 currentTaskId
     */
    void stopMessage(Long userId, String conversationId, String taskId);

    /**
     * 删除本地会话。
     *
     * @param userId 当前登录用户 ID
     * @param conversationId 本地会话 ID
     */
    void deleteConversation(Long userId, String conversationId);
}
