package com.zzyl.hospital.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
@ApiModel(value = "AdmissionApplyDto", description = "入院申请参数")
public class AdmissionApplyDto {

    @ApiModelProperty(value = "患者信息")
    private AdmissionPatientDto admissionPatientDto;

    @ApiModelProperty(value = "家属信息列表")
    private List<PatientFamilyDto> patientFamilyDtoList;

    @ApiModelProperty(value = "入院配置")
    private AdmissionConfigDto admissionConfigDto;

    @ApiModelProperty(value = "入院合同")
    private AdmissionContractDto admissionContractDto;
}
