package com.zzyl.hospital.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzyl.common.annotation.Excel;
import com.zzyl.hospital.domain.AdmissionConfig;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionConfigVo extends AdmissionConfig {

    /**
     * 入院开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入院开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime startDate;

    /**
     * 入院结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入院结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime endDate;

    /**
     * 入院病床
     */
    @Excel(name = "入院病床")
    private String wardBedNo;
}
