package com.zzyl.hospital.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * Family member entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="PatientContact", description="Family member profile")
public class PatientContact extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Primary key */
    @ApiModelProperty("Primary key")
    @TableId(type = IdType.AUTO)
    private Long id;

    /** Phone number */
    @Excel(name = "Phone")
    @ApiModelProperty("Phone")
    private String phone;

    /** Name */
    @Excel(name = "Name")
    @ApiModelProperty("Name")
    private String name;

    /** Avatar */
    @Excel(name = "Avatar")
    @ApiModelProperty("Avatar")
    private String avatar;

    /** OpenID */
    @Excel(name = "OpenID")
    @ApiModelProperty("OpenID")
    private String openId;

    /** Gender */
    @Excel(name = "Gender (0:Male,1:Female)")
    @ApiModelProperty("Gender (0:Male,1:Female)")
    private Integer gender;
}
