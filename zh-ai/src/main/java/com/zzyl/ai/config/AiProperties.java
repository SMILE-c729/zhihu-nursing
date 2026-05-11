package com.zzyl.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Dify 客户端配置。
 */
@Data
@Component
@ConfigurationProperties(prefix = "zzyl.ai.dify")
public class AiProperties
{
    /**
     * Dify 服务地址，例如 http://127.0.0.1/v1
     */
    private String baseUrl;

    /**
     * Dify 应用 API Key
     */
    private String apiKey;

    /**
     * 连接超时时间，单位：毫秒
     */
    private Integer connectTimeout = 5000;

    /**
     * 读取超时时间，单位：毫秒
     */
    private Integer readTimeout = 60000;

    /**
     * 写入超时时间，单位：毫秒
     */
    private Integer writeTimeout = 30000;
}
