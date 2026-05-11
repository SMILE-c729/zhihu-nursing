package com.zzyl.ai.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * AI 会话中的单条消息。
 */
@Data
public class AiConversationMessage implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 前端展示用消息 ID。
     */
    private String id;

    /**
     * 消息角色：user、assistant。
     */
    private String role;

    /**
     * 消息正文。
     */
    private String content;

    /**
     * 关联的 Dify task_id。
     */
    private String taskId;

    /**
     * 消息状态：completed、stopped、error。
     */
    private String status;

    /**
     * 消息创建时间。
     */
    private Date createTime;
}
