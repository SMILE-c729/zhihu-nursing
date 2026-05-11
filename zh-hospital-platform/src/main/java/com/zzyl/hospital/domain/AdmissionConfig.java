package com.zzyl.hospital.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 入院配置表对象 admission_config
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AdmissionConfig对象", description="入院配置表")
public class AdmissionConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 入院表ID */
    @Excel(name = "入院表ID")
    @ApiModelProperty("入院表ID")
    private Long admissionId;

    /** 护理级别ID */
    @Excel(name = "护理级别ID")
    @ApiModelProperty("护理级别ID")
    private Long careLevelId;

    /** 护理级别名称 */
    @Excel(name = "护理级别名称")
    @ApiModelProperty("护理级别名称")
    private String careLevelName;

    /** 费用开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "费用开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("费用开始时间")
    private LocalDateTime feeStartDate;

    /** 费用结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "费用结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("费用结束时间")
    private LocalDateTime feeEndDate;

    /** 押金（元） */
    @Excel(name = "押金", readConverterExp = "元=")
    @ApiModelProperty("押金（元）")
    private BigDecimal deposit;

    /** 照护费用（元/月） */
    @Excel(name = "照护费用", readConverterExp = "元=/月")
    @ApiModelProperty("照护费用（元/月）")
    private BigDecimal careFee;

    /** 病床费用（元/月） */
    @Excel(name = "病床费用", readConverterExp = "元=/月")
    @ApiModelProperty("病床费用（元/月）")
    private BigDecimal wardBedFee;

    /** 医保支付（元/月） */
    @Excel(name = "医保支付", readConverterExp = "元=/月")
    @ApiModelProperty("医保支付（元/月）")
    private BigDecimal insurancePayment;

    /** 政府补贴（元/月） */
    @Excel(name = "政府补贴", readConverterExp = "元=/月")
    @ApiModelProperty("政府补贴（元/月）")
    private BigDecimal governmentSubsidy;

    /** 其他费用（元/月） */
    @Excel(name = "其他费用", readConverterExp = "元=/月")
    @ApiModelProperty("其他费用（元/月）")
    private BigDecimal otherFees;

    /** 排序编号 */
    @Excel(name = "排序编号")
    @ApiModelProperty("排序编号")
    private Integer sortOrder;

}
