package com.zzyl.hospital.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 照护方案对象 care_plan
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="CarePlan对象", description="照护方案")
public class CarePlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 排序号 */
    @Excel(name = "排序号")
    @ApiModelProperty("排序号")
    private Integer sortNo;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String planName;

    /** 状态 0禁用 1启用 */
    @Excel(name = "状态 0禁用 1启用")
    @ApiModelProperty("状态 0禁用 1启用")
    private Integer status;

}
