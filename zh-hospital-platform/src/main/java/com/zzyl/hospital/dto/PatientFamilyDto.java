package com.zzyl.hospital.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PatientFamilyDto", description = "家属信息")
public class PatientFamilyDto {

    @ApiModelProperty(value = "亲属关系")
    private String kinship;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;
}
