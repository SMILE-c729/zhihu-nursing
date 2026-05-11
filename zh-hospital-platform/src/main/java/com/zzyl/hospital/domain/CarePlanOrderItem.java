package com.zzyl.hospital.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 照护方案和项目关联对象 care_plan_order_item
 * 
 * @author alexis
 * @date 2025-06-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="CarePlanOrderItem对象", description="照护方案和项目关联")
public class CarePlanOrderItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("${column.columnComment}")
    private Long id;

    /** 计划id */
    @Excel(name = "计划id")
    @ApiModelProperty("计划id")
    private Long carePlanId;

    /** 项目id */
    @Excel(name = "项目id")
    @ApiModelProperty("项目id")
    private Long medicalOrderItemId;

    /** 计划执行时间 */
    @Excel(name = "计划执行时间")
    @ApiModelProperty("计划执行时间")
    private String executeTime;

    /** 执行周期 0 天 1 周 2月 */
    @Excel(name = "执行周期 0 天 1 周 2月")
    @ApiModelProperty("执行周期 0 天 1 周 2月")
    private Integer executeCycle;

    /** 执行频次 */
    @Excel(name = "执行频次")
    @ApiModelProperty("执行频次")
    private Integer executeFrequency;

}
