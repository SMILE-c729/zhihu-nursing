package com.zzyl.ai.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * AI 会话历史摘要。
 */
@Data
public class AiConversationSummaryVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String conversationId;

    private String title;

    private String status;

    private Date updateTime;

    private Integer messageCount;

    private String lastMessage;
}
