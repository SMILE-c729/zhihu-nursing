package com.zzyl.ai.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AI 停止生成请求对象。
 */
@Data
@ApiModel("AI 停止生成请求")
public class AiChatStopRequest
{
    /**
     * 本地会话 ID。
     */
    @ApiModelProperty(value = "本地会话 ID", required = true)
    private String conversationId;

    /**
     * 会话 ID 兼容字段。
     */
    @ApiModelProperty(value = "会话 ID，兼容前端 sessionId 命名")
    private String sessionId;

    /**
     * Dify 当前任务 ID。
     */
    @ApiModelProperty(value = "Dify 当前任务 ID")
    private String taskId;
}
