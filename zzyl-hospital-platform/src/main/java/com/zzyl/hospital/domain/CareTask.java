package com.zzyl.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 照护任务对象 care_task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CareTask对象", description = "照护任务")
public class CareTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @Excel(name = "责任护士id")
    @ApiModelProperty("责任护士id")
    private String nurseId;

    @Excel(name = "项目id")
    @ApiModelProperty("项目id")
    private Integer medicalOrderItemId;

    @Excel(name = "医嘱项目名称")
    @ApiModelProperty("医嘱项目名称")
    private String medicalOrderItemName;

    @Excel(name = "患者ID")
    @ApiModelProperty("患者ID")
    private Long patientId;

    @Excel(name = "患者姓名")
    @ApiModelProperty("患者姓名")
    private String patientName;

    @Excel(name = "病床编号")
    @ApiModelProperty("病床编号")
    private String wardBedNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预计服务时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("预计服务时间")
    private Date estimatedServerTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实际服务时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("实际服务时间")
    private Date realServerTime;

    @Excel(name = "执行记录")
    @ApiModelProperty("执行记录")
    private String mark;

    @Excel(name = "取消原因")
    @ApiModelProperty("取消原因")
    private String cancelReason;

    @Excel(name = "状态 1待执行 2已执行 3已关闭")
    @ApiModelProperty("状态 1待执行 2已执行 3已关闭")
    private Integer status;

    @Excel(name = "执行图片")
    @ApiModelProperty("执行图片")
    private String taskImage;
}
