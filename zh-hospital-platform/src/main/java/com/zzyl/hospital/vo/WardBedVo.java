package com.zzyl.hospital.vo;

import com.zzyl.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("病床Vo")
public class WardBedVo {

    @ApiModelProperty(value = "病床ID")
    private Long id;

    /**
     * 病床号
     */
    @ApiModelProperty(value = "病床号")
    private String wardBedNo;

    /**
     * 病床状态: 未入院0, 已入院1 入院申请中2
     */
    @ApiModelProperty(value = "病床状态: 未入院0, 已入院1 入院申请中2")
    private Integer wardBedStatus;

    /**
     * 房间ID
     */
    @ApiModelProperty(value = "房间ID")
    private Long wardRoomId;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String ename;

    /**
     * 患者ID
     */
    @ApiModelProperty(value = "患者ID")
    private Long patientId;

    @ApiModelProperty(value = "责任护士")
    private List<SysUser> userVos;

    @ApiModelProperty(value = "病床设备列表")
    private List<MonitoringDeviceVo> monitoringDeviceVos;
    /**
     * 关联的设备
     */
    private List<MonitoringDeviceInfo> MonitoringDevices;

}
