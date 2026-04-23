package com.zzyl.hospital.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 患者对象 patient
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Patient对象", description="患者")
public class Patient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("id")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String name;

    /** 图片 */
    @Excel(name = "图片")
    @ApiModelProperty("图片")
    private String image;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    private String idCardNo;

    /** 性别（0:女  1:男） */
    @Excel(name = "性别", readConverterExp = "0=:女,1=:男")
    @ApiModelProperty("性别（0:女  1:男）")
    private Integer sex;

    /** 状态（0：禁用，1:启用  2:请假 3:出院中 4入院中 5已出院） */
    @Excel(name = "状态", readConverterExp = "0=：禁用，1:启用,2=:请假,3=:出院中,4=入院中,5=已出院")
    @ApiModelProperty("状态（0：禁用，1:启用  2:请假 3:出院中 4入院中 5已出院）")
    private Integer status;

    /** 手机号 */
    @Excel(name = "手机号")
    @ApiModelProperty("手机号")
    private String phone;

    /** 出生日期 */
    @Excel(name = "出生日期")
    @ApiModelProperty("出生日期")
    private String birthday;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    @ApiModelProperty("家庭住址")
    private String address;

    /** 身份证国徽面 */
    @Excel(name = "身份证国徽面")
    @ApiModelProperty("身份证国徽面")
    private String idCardNationalEmblemImg;

    /** 身份证人像面 */
    @Excel(name = "身份证人像面")
    @ApiModelProperty("身份证人像面")
    private String idCardPortraitImg;

    /** 病床编号 */
    @Excel(name = "病床编号")
    @ApiModelProperty("病床编号")
    private String wardBedNo;

    /** 病床id */
    @Excel(name = "病床id")
    @ApiModelProperty("病床id")
    private Long wardBedId;

}
