package com.zzyl.hospital.vo;

import com.zzyl.hospital.domain.AdmissionConfig;
import com.zzyl.hospital.domain.AdmissionContract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 入院详情响应模型
 *
 * @author itcast
 * @create 2023/12/19 14:58
 **/
@ApiModel(description = "入院详情响应模型")
@Data
public class AdmissionDetailVo {
    /**
     * 患者信息
     */
    @ApiModelProperty(value = "患者响应信息")
    private AdmissionPatientVo admissionPatientVo;

    /**
     * 家属信息
     */
    @ApiModelProperty(value = "家属响应信息")
    private List<PatientFamilyVo> patientFamilyVoList;

    /**
     * 入院配置
     */
    @ApiModelProperty(value = "入院配置响应信息")
    private AdmissionConfigVo admissionConfigVo;

    /**
     * 签约办理
     */
    @ApiModelProperty(value = "签约办理响应信息")
    private AdmissionContract admissionContract;

}
