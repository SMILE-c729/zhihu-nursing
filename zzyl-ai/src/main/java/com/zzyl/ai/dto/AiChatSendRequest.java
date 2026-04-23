package com.zzyl.ai.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AI 对话发送消息请求对象。
 */
@Data
@ApiModel("AI 发送消息请求")
public class AiChatSendRequest
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
     * 用户输入的消息内容。
     */
    @ApiModelProperty(value = "用户消息内容", required = true)
    private String content;
}
