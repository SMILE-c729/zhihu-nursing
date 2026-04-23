package com.zzyl.hospital.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("房间Vo")
public class WardRoomVo {

    @ApiModelProperty(value = "房间ID", required = true)
    private Long id;

    @ApiModelProperty(value = "楼层名称", required = true)
    private String wardFloorName;

    @ApiModelProperty(value = "楼层ID", required = true)
    private String wardFloorId;

    @ApiModelProperty(value = "房间ID", required = true)
    private String wardRoomId;

    @ApiModelProperty(value = "房间编号", required = true)
    private String code;

    @ApiModelProperty(value = "房间价格", required = true)
    private String price;

    @ApiModelProperty(value = "病床列表", required = true)
    private List<WardBedVo> wardBedVoList;

    @ApiModelProperty(value = "房间设备列表")
    private List<MonitoringDeviceVo> monitoringDeviceVos;
    /**
     * 关联的设备
     */
    private List<MonitoringDeviceInfo> MonitoringDevices;
}
