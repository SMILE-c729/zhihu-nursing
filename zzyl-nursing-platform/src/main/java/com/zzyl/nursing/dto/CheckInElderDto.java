package com.zzyl.nursing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CheckInElderDto", description = "入住老人信息")
public class CheckInElderDto {

    @ApiModelProperty(value = "老人姓名")
    private String name;

    @ApiModelProperty(value = "性别(0女,1男)")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "出生日期(yyyy-MM-dd)")
    private String birthday;

    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "头像地址")
    private String image;

    @ApiModelProperty(value = "身份证国徽面")
    private String idCardNationalEmblemImg;

    @ApiModelProperty(value = "身份证人像面")
    private String idCardPortraitImg;
}
