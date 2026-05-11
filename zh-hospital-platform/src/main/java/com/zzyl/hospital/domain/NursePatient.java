package com.zzyl.hospital.domain;

import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 责任护士患者关联对象 nurse_patient
 *
 * @author ruoyi
 * @date 2024-05-28
 */
@Data
public class NursePatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 责任护士id */
    @Excel(name = "责任护士id")
    private Long nurseId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long patientId;
}
