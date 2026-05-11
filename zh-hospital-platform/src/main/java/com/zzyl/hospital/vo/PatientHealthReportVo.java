package com.zzyl.hospital.vo;

import lombok.Data;

import java.util.List;

/**
 * 体检报告
 * @author itheima
 */
@Data
public class PatientHealthReportVo {

    /**
     * 体检日期
     */
    private String totalCheckDate;
    /**
     * 入院评估
     */
    private PatientAssessmentVo patientAssessment;
    /**
     * 风险分布
     */
    private RiskDistributionVo riskDistribution;
    /**
     * 异常数据列表
     */
    private List<AbnormalDataVo> abnormalData;

    /**
     * 健康系统分值
     */
    private SystemScore systemScore;

    /**
     * 综合总结
     */
    private String summarize;
}
