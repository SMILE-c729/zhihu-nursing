package com.zzyl.hospital.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("责任护士与患者关系Dto")
public class NursePatientDto {

    @ApiModelProperty(value = "患者ID")
    private Long patientId;

    @ApiModelProperty(value = "责任护士id列表")
    private List<Long> nurseIds;
}
