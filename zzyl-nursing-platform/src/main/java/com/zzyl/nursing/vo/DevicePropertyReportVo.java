package com.zzyl.nursing.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 设备属性上报实体类
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DevicePropertyReportVo {
    
    /**
     * 资源类型
     */
    private String resource;
    
    /**
     * 事件类型
     */
    private String event;
    
    /**
     * 事件时间
     */
    @JsonProperty("event_time")
    private String eventTime;
    
    /**
     * 事件时间（毫秒）
     */
    @JsonProperty("event_time_ms")
    private String eventTimeMs;
    
    /**
     * 请求ID
     */
    @JsonProperty("request_id")
    private String requestId;
    
    /**
     * 通知数据
     */
    @JsonProperty("notify_data")
    private NotifyData notifyData;


}
