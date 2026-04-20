package com.zzyl.nursing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 设备指标统计查询参数
 */
@Data
@ApiModel("设备指标统计查询参数")
public class DeviceStatisticsQueryDto {

    /**
     * 设备id
     */
    @ApiModelProperty("设备id")
    private String iotId;

    /**
     * 指标标识
     */
    @ApiModelProperty("指标标识")
    private String functionId;

    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "查询日期，格式：yyyy-MM-dd")
    private LocalDate date;
}
