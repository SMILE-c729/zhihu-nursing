package com.zzyl.hospital.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 时间段剩余预约次数
 */
@Data
@ApiModel(value = "AppointmentTimeCountVo", description = "时间段剩余预约次数")
public class AppointmentTimeCountVo {

    /** 时间段 */
    @ApiModelProperty("时间段")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /** 剩余预约次数 */
    @ApiModelProperty("剩余预约次数")
    private Integer count;
}
