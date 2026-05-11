package com.zzyl.hospital.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@ApiModel(value = "AdmissionConfigDto", description = "入院配置")
public class AdmissionConfigDto {

    @ApiModelProperty(value = "病床ID")
    private Long wardBedId;

    @ApiModelProperty(value = "房间ID")
    private Long wardRoomId;

    @ApiModelProperty(value = "楼层ID")
    private Long wardFloorId;

    @ApiModelProperty(value = "楼层名称")
    private String wardFloorName;

    @ApiModelProperty(value = "房间编号")
    private String code;

    @ApiModelProperty(value = "护理级别ID")
    private Long careLevelId;

    @ApiModelProperty(value = "护理级别名称")
    private String careLevelName;

    @ApiModelProperty(value = "病床费")
    private BigDecimal wardBedFee;

    @ApiModelProperty(value = "押金")
    private BigDecimal deposit;

    @ApiModelProperty(value = "护理费")
    private BigDecimal careFee;

    @ApiModelProperty(value = "医保支付")
    private BigDecimal insurancePayment;

    @ApiModelProperty(value = "政府补贴")
    private BigDecimal governmentSubsidy;

    @ApiModelProperty(value = "其他费用")
    private BigDecimal otherFees;

    @ApiModelProperty(value = "入院开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "入院结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "费用开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime feeStartDate;

    @ApiModelProperty(value = "费用结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime feeEndDate;
}
