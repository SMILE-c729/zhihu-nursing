package com.zzyl.hospital.domain;

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
 * 预警数据对象 warning_data
 * 
 * @author alexis
 * @date 2025-07-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="WarningData对象", description="预警数据")
public class WarningData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 物联网设备id */
    @Excel(name = "物联网设备id")
    @ApiModelProperty("物联网设备id")
    private String iotId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    @ApiModelProperty("设备名称")
    private String monitoringDeviceName;

    /** 所属产品key */
    @Excel(name = "所属产品key")
    @ApiModelProperty("所属产品key")
    private String productKey;

    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String productName;

    /** 功能标识符 */
    @Excel(name = "功能标识符")
    @ApiModelProperty("功能标识符")
    private String functionId;

    /** 接入位置 */
    @Excel(name = "接入位置")
    @ApiModelProperty("接入位置")
    private String accessLocation;

    /** 位置类型 0：随身设备 1：固定设备 */
    @Excel(name = "位置类型 0：随身设备 1：固定设备")
    @ApiModelProperty("位置类型 0：随身设备 1：固定设备")
    private Integer locationType;

    /** 物理位置类型 0楼层 1房间 2病床 */
    @Excel(name = "物理位置类型 0楼层 1房间 2病床")
    @ApiModelProperty("物理位置类型 0楼层 1房间 2病床")
    private Integer physicalLocationType;

    /** 位置备注 */
    @Excel(name = "位置备注")
    @ApiModelProperty("位置备注")
    private String monitoringDeviceDescription;

    /** 数据值 */
    @Excel(name = "数据值")
    @ApiModelProperty("数据值")
    private String dataValue;

    /** 预警规则id */
    @Excel(name = "预警规则id")
    @ApiModelProperty("预警规则id")
    private Long warningRuleId;

    /** 预警原因，格式：功能名称+运算符+阈值+持续周期+聚合周期 */
    @Excel(name = "预警原因，格式：功能名称+运算符+阈值+持续周期+聚合周期")
    @ApiModelProperty("预警原因，格式：功能名称+运算符+阈值+持续周期+聚合周期")
    private String warningReason;

    /** 处理结果 */
    @Excel(name = "处理结果")
    @ApiModelProperty("处理结果")
    private String processingResult;

    /** 处理人id */
    @Excel(name = "处理人id")
    @ApiModelProperty("处理人id")
    private Long processorId;

    /** 处理人名称 */
    @Excel(name = "处理人名称")
    @ApiModelProperty("处理人名称")
    private String processorName;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("处理时间")
    private LocalDateTime processingTime;

    /** 预警数据类型，0：患者异常数据，1：设备异常数据 */
    @Excel(name = "预警数据类型，0：患者异常数据，1：设备异常数据")
    @ApiModelProperty("预警数据类型，0：患者异常数据，1：设备异常数据")
    private Integer type;

    /** 状态，0：待处理，1：已处理 */
    @Excel(name = "状态，0：待处理，1：已处理")
    @ApiModelProperty("状态，0：待处理，1：已处理")
    private Integer status;

    /** 接收人id */
    @Excel(name = "接收人id")
    @ApiModelProperty("接收人id")
    private Long userId;

}
