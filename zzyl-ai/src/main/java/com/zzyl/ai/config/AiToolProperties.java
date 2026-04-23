package com.zzyl.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI 工具接口配置。
 */
@Data
@Component
@ConfigurationProperties(prefix = "zzyl.ai.tool")
public class AiToolProperties
{
    /**
     * Dify 调用工具接口时使用的访问密钥
     */
    private String apiKey = "demo-dify-tool-key";
}
