package com.zzyl.hospital.vo;

import com.zzyl.hospital.domain.VitalSignData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 设备响应对象
 */
@Data
@ApiModel("设备响应对象")
public class MonitoringDeviceVo {

    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "物联网设备ID")
    private String iotId;

    @ApiModelProperty(value = "设备名称")
    private String monitoringDeviceName;

    @ApiModelProperty(value = "产品Key")
    private String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "设备数据列表")
    private List<VitalSignDataVo> vitalSignDataVos;
}
