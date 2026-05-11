package com.zzyl.ai.vo;

import lombok.Data;

import java.util.Map;

/**
 * Dify workflow 执行结果。
 */
@Data
public class AiWorkflowRunResponse
{
    /**
     * Dify task_id。
     */
    private String taskId;

    /**
     * Dify workflow_run_id。
     */
    private String workflowRunId;

    /**
     * Workflow 执行状态。
     */
    private String status;

    /**
     * Workflow 输出中的文本答案。
     */
    private String answer;

    /**
     * Workflow 原始 outputs。
     */
    private Map<String, Object> outputs;

    /**
     * Workflow 错误信息。
     */
    private String error;
}
