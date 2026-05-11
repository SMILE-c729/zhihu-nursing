package com.zzyl.hospital.vo;

import lombok.Data;

@Data
public class MedicalOrderItemVo {
    /**
     * 项目名称
     */
    private String label;

    /**
     * 项目ID
     */
    private String value;
}