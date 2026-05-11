package com.zzyl.hospital.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 入院对象 admission
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Admission对象", description="入院")
public class Admission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    @ApiModelProperty("患者姓名")
    private String patientName;

    /** 患者ID */
    @Excel(name = "患者ID")
    @ApiModelProperty("患者ID")
    private Long patientId;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    private String idCardNo;

    /** 入院开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入院开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("入院开始时间")
    private LocalDateTime startDate;

    /** 入院结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入院结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("入院结束时间")
    private LocalDateTime endDate;

    /** 护理级别名称 */
    @Excel(name = "护理级别名称")
    @ApiModelProperty("护理级别名称")
    private String careLevelName;

    /** 入院病床 */
    @Excel(name = "入院病床")
    @ApiModelProperty("入院病床")
    private String wardBedNo;

    /** 状态 (0: 已入院, 1: 已出院) */
    @Excel(name = "状态 (0: 已入院, 1: 已出院)")
    @ApiModelProperty("状态 (0: 已入院, 1: 已出院)")
    private Integer status;

    /** 排序编号 */
    @Excel(name = "排序编号")
    @ApiModelProperty("排序编号")
    private Integer sortOrder;

}
