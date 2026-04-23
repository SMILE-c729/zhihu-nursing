package com.zzyl.hospital.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "照护方案")
public class CarePlanVo {

    /**
     * 照护方案id
     */
    private Long id;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "照护方案排序号")
    private Integer sortNo;

    @ApiModelProperty(value = "照护方案名称")
    private String planName;

    @ApiModelProperty(value = "状态（0：禁用，1：启用）")
    private Integer status;

    @ApiModelProperty(value = "照护方案项目列表")
    List<CarePlanOrderItemVo> projectPlans;

}