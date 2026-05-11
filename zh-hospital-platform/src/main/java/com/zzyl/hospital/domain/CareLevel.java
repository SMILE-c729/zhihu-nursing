package com.zzyl.hospital.domain;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理级别对象 care_level
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="CareLevel对象", description="护理级别")
public class CareLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 等级名称 */
    @Excel(name = "等级名称")
    @ApiModelProperty("等级名称")
    private String name;

    /** 照护方案ID */
    @Excel(name = "照护方案ID")
    @ApiModelProperty("照护方案ID")
    private Long carePlanId;

    /** 照护费用 */
    @Excel(name = "照护费用")
    @ApiModelProperty("照护费用")
    private BigDecimal fee;

    /** 状态（0：禁用，1：启用） */
    @Excel(name = "状态", readConverterExp = "0=：禁用，1：启用")
    @ApiModelProperty("状态（0：禁用，1：启用）")
    private Integer status;

    /** 等级说明 */
    @Excel(name = "等级说明")
    @ApiModelProperty("等级说明")
    private String description;

}
