package com.zzyl.ai.domain;

import lombok.Data;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AI 会话映射信息。
 *
 * <p>用于在本地会话 ID 与 Dify 会话 ID 之间建立映射关系。</p>
 */
@Data
public class AiConversationSession implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 本地会话 ID，提供给前端使用。
     */
    private String localConversationId;

    /**
     * Dify 返回的会话 ID。
     */
    private String difyConversationId;

    /**
     * 会话标题，默认由第一条用户问题生成。
     */
    private String title;

    /**
     * 当前会话状态：new、running、completed、stopped、error。
     */
    private String status;

    /**
     * 当前正在运行的 Dify task_id。
     */
    private String currentTaskId;

    /**
     * 最近一次 Dify workflow_run_id。
     */
    private String workflowRunId;

    /**
     * Redis 临时保存的消息历史。
     */
    private List<AiConversationMessage> messages = new ArrayList<>();

    /**
     * 当前登录用户 ID。
     */
    private Long userId;

    /**
     * 当前登录用户名。
     */
    private String username;

    /**
     * 发送给 Dify 的用户唯一标识。
     */
    private String difyUser;

    /**
     * 会话创建时间。
     */
    private Date createTime;

    /**
     * 会话最近更新时间。
     */
    private Date updateTime;
}
