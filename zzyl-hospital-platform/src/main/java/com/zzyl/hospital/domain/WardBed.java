package com.zzyl.hospital.domain;

import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 病床对象 wardBed
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Data
@ApiModel("病床实体对象")
public class WardBed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 病床ID */
    @ApiModelProperty(value = "病床ID")
    private Long id;

    /** 病床编号 */
    @Excel(name = "病床编号")
    @ApiModelProperty(value = "病床编号")
    private String wardBedNo;

    /** 病床状态: 未入院0, 已入院1 入院申请中2 */
    @Excel(name = "病床状态: 未入院0, 已入院1 入院申请中2")
    @ApiModelProperty(value = "病床状态: 未入院0, 已入院1 入院申请中2")
    private Integer wardBedStatus;

    /** 病床号 */
    @Excel(name = "病床号")
    @ApiModelProperty(value = "病床号")
    private Long sort;

    /** 房间ID */
    @Excel(name = "房间ID")
    @ApiModelProperty(value = "房间ID")
    private Long wardRoomId;

}
