package com.zzyl.hospital.vo;

import lombok.Data;

/**
 *  入院评估类
 * @author itheima
 */
@Data
public class PatientAssessmentVo {
    /**
     * 健康风险等级
     */
    private String riskLevel;
    /**
     * 健康指数
     */
    private double healthIndex;

}