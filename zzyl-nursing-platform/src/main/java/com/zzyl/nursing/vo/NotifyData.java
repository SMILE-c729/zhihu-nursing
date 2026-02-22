package com.zzyl.nursing.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 通知数据实体类
 */
@Data
public class NotifyData {

    /**
     * 请求头信息
     */
    private Header header;

    /**
     * 请求体信息
     */
    private Body body;

    /**
     * 请求头信息
     */
    @Data
    public static class Header {

        /**
         * 应用ID
         */
        @JsonProperty("app_id")
        private String appId;

        /**
         * 设备ID
         */
        @JsonProperty("device_id")
        private String deviceId;

        /**
         * 节点ID
         */
        @JsonProperty("node_id")
        private String nodeId;

        /**
         * 产品ID
         */
        @JsonProperty("product_id")
        private String productId;

        /**
         * 网关ID
         */
        @JsonProperty("gateway_id")
        private String gatewayId;
    }

    /**
     * 请求体信息
     */
    @Data
    public static class Body {

        /**
         * 服务列表
         */
        private List<Service> services;
    }

    /**
     * 服务信息
     */
    @Data
  public   static class Service {

        /**
         * 服务ID
         */
        @JsonProperty("service_id")
        private String serviceId;

        /**
         * 属性信息
         */
        private Map<String, Object> properties;

        /**
         * 事件时间
         */
        @JsonProperty("event_time")
        private String eventTime;
    }
}