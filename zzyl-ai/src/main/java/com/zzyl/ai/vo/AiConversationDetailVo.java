package com.zzyl.ai.vo;

import com.zzyl.ai.domain.AiConversationMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AI 会话详情。
 */
@Data
public class AiConversationDetailVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String conversationId;

    private String title;

    private String status;

    private String currentTaskId;

    private String workflowRunId;

    private Date createTime;

    private Date updateTime;

    private List<AiConversationMessage> messages;
}
