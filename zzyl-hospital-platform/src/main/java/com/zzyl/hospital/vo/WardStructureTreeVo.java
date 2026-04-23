package com.zzyl.hospital.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WardStructureTreeVo {
    private String value;
    private String label;

    /**
     * 子节点列表，使用@JsonInclude注解确保在序列化为JSON时，
     * 如果children列表为空或为null，则不会包含在输出的JSON中
     */
     @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<WardStructureTreeVo> children;
}
