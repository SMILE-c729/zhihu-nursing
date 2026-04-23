package com.zzyl.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预警通知消息对象
 *
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningNotifyVo {
    /**
     * 预警数据id
     */
    private Long id;

    /**
     * 接入位置
     */
    private String accessLocation;

    /**
     * 位置类型 0：随身设备 1：固定设备
     */
    private Integer locationType;

    /**
     * 物理位置类型 0楼层 1房间 2病床
     */
    private Integer physicalLocationType;

    /**
     * 设备位置
     */
    private String monitoringDeviceDescription;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 数据值
     */
    private String dataValue;

    /**
     * 预警数据类型，0：患者异常数据，1：设备异常数据
     */
    private Integer warningDataType;

    /**
     * 语音通知状态，0：关闭，1：开启
     */
    private Integer voiceNotifyStatus;

    /**
     * 预警通知类型，0：解除预警，1：预警
     */
    private Integer notifyType;

    /**
     * 是否全员通知<br>
     * 智能病床的预警消息是全员通知，对于责任护士和固定设备维护人员不是全员通知
     */
    private Boolean isAllConsumer;
}