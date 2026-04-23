package com.zzyl.ai.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前照护方案响应对象。
 *
 * <p>该对象专门面向 Dify Tool 调用场景，字段做了精简，只保留关键数据。</p>
 */
@Data
public class AiCurrentPlansVo
{
    /**
     * 当前启用中的照护方案总数。
     */
    private Integer totalPlanCount;

    /**
     * 照护方案列表。
     */
    private List<PlanItem> plans = new ArrayList<>();

    /**
     * 单个照护方案摘要。
     */
    @Data
    public static class PlanItem
    {
        /**
         * 照护方案 ID。
         */
        private Long planId;

        /**
         * 照护方案名称。
         */
        private String planName;

        /**
         * 当前照护方案下的项目列表。
         */
        private List<ProjectItem> projects = new ArrayList<>();
    }

    /**
     * 照护方案中的项目摘要。
     */
    @Data
    public static class ProjectItem
    {
        /**
         * 医嘱项目 ID。
         */
        private Long projectId;

        /**
         * 医嘱项目名称。
         */
        private String projectName;

        /**
         * 执行时间。
         */
        private String executeTime;

        /**
         * 执行周期中文说明，例如“天 / 周 / 月”。
         */
        private String executeCycleLabel;

        /**
         * 执行频次。
         */
        private Long executeFrequency;
    }
}
