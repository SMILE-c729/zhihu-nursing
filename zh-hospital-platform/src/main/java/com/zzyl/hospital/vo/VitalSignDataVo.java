package com.zzyl.hospital.vo;

import com.zzyl.hospital.domain.VitalSignData;
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
public class VitalSignDataVo extends VitalSignData {

    @ApiModelProperty(value = "设备昵称")
    private String nickname;
}
