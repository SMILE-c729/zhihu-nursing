package com.zzyl.hospital.domain;

import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 病房类型对象 wardRoom_type
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Data
public class WardRoomType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 病房类型名称 */
    @Excel(name = "病房类型名称")
    private String name;

    /** 病床数量 */
    @Excel(name = "病床数量")
    private Long wardBedCount;

    /** 病床费用 */
    @Excel(name = "病床费用")
    private BigDecimal price;

    /** 介绍 */
    @Excel(name = "介绍")
    private String introduction;

    /** 照片 */
    @Excel(name = "照片")
    private String photo;

    /** 状态，0：禁用，1：启用 */
    @Excel(name = "状态，0：禁用，1：启用")
    private Long status;

}
