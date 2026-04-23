package com.zzyl.hospital.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@ApiModel(value = "AdmissionContractDto", description = "入院合同信息")
public class AdmissionContractDto {

    @ApiModelProperty(value = "合同名称")
    private String admissionContractName;

    @ApiModelProperty(value = "协议地址")
    private String agreementPath;

    @ApiModelProperty(value = "第三方姓名")
    private String thirdPartyName;

    @ApiModelProperty(value = "第三方电话")
    private String thirdPartyPhone;

    @ApiModelProperty(value = "签约时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signDate;
}
