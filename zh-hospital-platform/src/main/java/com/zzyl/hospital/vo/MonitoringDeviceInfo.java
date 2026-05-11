package com.zzyl.hospital.vo;

import com.zzyl.hospital.domain.VitalSignData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("设备信息响应模型")
@Data
public class MonitoringDeviceInfo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "物联网设备ID")
    private String iotId;

    @ApiModelProperty(value = "设备名称")
    private String monitoringDeviceName;

    @ApiModelProperty(value = "产品key")
    public String productKey;

    @ApiModelProperty(value = "产品名称")
    public String productName;

    @ApiModelProperty(value = "设备数据")
    private List<VitalSignData> vitalSignDataVos;
}