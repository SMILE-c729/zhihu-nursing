package com.zzyl.hospital.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zzyl.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 护理级别对象 care_level
 * 
 * @author ruoyi
 * @date 2024-10-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("护理级别实体")
public class CareLevelVo {
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 等级名称
    */
    @ApiModelProperty(value = "等级名称")
    private String name;

    /**
    * 照护方案ID
    */
    @ApiModelProperty(value = "照护方案ID")
    private Long carePlanId;

    /**
    * 照护费用
    */
    @ApiModelProperty(value = "照护费用")
    private BigDecimal fee;

    /**
    * 状态（0：禁用，1：启用）
    */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
    * 等级说明
    */
    @ApiModelProperty(value = "等级说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "关联照护方案名称")
    private String planName;
}
