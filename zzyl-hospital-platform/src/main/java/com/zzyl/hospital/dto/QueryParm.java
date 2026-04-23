package com.zzyl.hospital.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "查询参数")
public class QueryParm {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "页码")
    private int pageNum;

    @ApiModelProperty(value = "页大小")
    private int pageSize;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
