package com.zzyl.hospital.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 设备对象 monitoringDevice
 * 
 * @author alexis
 * @date 2026-02-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="MonitoringDevice对象", description="设备")
public class MonitoringDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 物联网设备ID */
    @Excel(name = "物联网设备ID")
    @ApiModelProperty("物联网设备ID")
    private String iotId;

    /** 设备秘钥 */
    @Excel(name = "设备秘钥")
    @ApiModelProperty("设备秘钥")
    private String secret;

    /** 绑定位置 */
    @Excel(name = "绑定位置")
    @ApiModelProperty("绑定位置")
    private String bindingLocation;

    /** 位置类型 0：随身设备 1：固定设备 */
    @Excel(name = "位置类型 0：随身设备 1：固定设备")
    @ApiModelProperty("位置类型 0：随身设备 1：固定设备")
    private Integer locationType;

    /** 物理位置类型 0楼层 1房间 2病床 */
    @Excel(name = "物理位置类型 0楼层 1房间 2病床")
    @ApiModelProperty("物理位置类型 0楼层 1房间 2病床")
    private Integer physicalLocationType;

    /** 设备名称 */
    @Excel(name = "设备名称")
    @ApiModelProperty("设备名称")
    private String monitoringDeviceName;

    /** 产品key */
    @Excel(name = "产品key")
    @ApiModelProperty("产品key")
    private String productKey;

    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String productName;

    /** 位置备注 */
    @Excel(name = "位置备注")
    @ApiModelProperty("位置备注")
    private String monitoringDeviceDescription;

    /** 产品是否包含门禁，0：否，1：是 */
    @Excel(name = "产品是否包含门禁，0：否，1：是")
    @ApiModelProperty("产品是否包含门禁，0：否，1：是")
    private Integer haveEntranceGuard;

    /** 节点id */
    @Excel(name = "节点id")
    @ApiModelProperty("节点id")
    private String nodeId;

}
