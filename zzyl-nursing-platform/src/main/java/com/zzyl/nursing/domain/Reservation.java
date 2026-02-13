package com.zzyl.nursing.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzyl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预约信息实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Reservation对象", description = "预约信息")
public class Reservation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 预约人姓名 */
    @ApiModelProperty("预约人姓名")
    private String name;

    /** 预约人手机号 */
    @ApiModelProperty("预约人手机号")
    private String mobile;

    /** 预约时间 */
    @ApiModelProperty("预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /** 访客姓名 */
    @ApiModelProperty("访客姓名")
    private String visitor;

    /** 预约类型 */
    @ApiModelProperty("预约类型")
    private Integer type;

    /** 预约状态（0：已预约，2：已取消） */
    @ApiModelProperty("预约状态（0：已预约，2：已取消）")
    private Integer status;
}
