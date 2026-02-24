package com.zzyl.nursing.vo;

import com.zzyl.nursing.domain.DeviceData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备数据响应对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("设备数据响应对象")
public class DeviceDataVo extends DeviceData {

    @ApiModelProperty(value = "设备昵称")
    private String nickname;
}
