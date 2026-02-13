package com.zzyl.nursing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 新增预约参数
 */
@Data
@ApiModel(value = "ReservationCreateDto", description = "新增预约参数")
public class ReservationCreateDto {

    /** 预约人手机号 */
    @ApiModelProperty("预约人手机号")
    private String mobile;

    /** 预约人姓名 */
    @ApiModelProperty("预约人姓名")
    private String name;

    /** 预约时间 */
    @ApiModelProperty("预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /** 预约类型 */
    @ApiModelProperty("预约类型")
    private Integer type;

    /** 访客姓名 */
    @ApiModelProperty("访客姓名")
    private String visitor;
}
