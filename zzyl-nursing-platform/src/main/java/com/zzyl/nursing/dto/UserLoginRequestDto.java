package com.zzyl.nursing.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Client login request.
 */
@Data
public class UserLoginRequestDto {

    @ApiModelProperty("Nickname")
    private String nickName;

    @ApiModelProperty("WeChat temporary code")
    private String code;

    @ApiModelProperty("Encrypted phone code")
    private String phoneCode;
}
