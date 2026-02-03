package com.zzyl.nursing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
@ApiModel(value = "CheckInApplyDto", description = "入住申请参数")
public class CheckInApplyDto {

    @ApiModelProperty(value = "老人信息")
    private CheckInElderDto checkInElderDto;

    @ApiModelProperty(value = "家属信息列表")
    private List<ElderFamilyDto> elderFamilyDtoList;

    @ApiModelProperty(value = "入住配置")
    private CheckInConfigDto checkInConfigDto;

    @ApiModelProperty(value = "入住合同")
    private CheckInContractDto checkInContractDto;
}
