package com.zzyl.hospital.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 设备指标统计返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("设备指标统计返回对象")
public class MonitoringDeviceMetricsStatVo {

    /**
     * 时间维度（按天：HH:mm；按周：MM.dd）
     */
    @ApiModelProperty("时间维度（按天：HH:mm；按周：MM.dd）")
    private String dateTime;

    /**
     * 指标值
     */
    @ApiModelProperty("指标值")
    private BigDecimal dataValue;
}
