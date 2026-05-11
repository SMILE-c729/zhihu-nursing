package com.zzyl.hospital.vo;

import lombok.Data;

@Data
public class MedicalOrderNameVo {

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 责任护士姓名
     */
    private String nurseName;
}
