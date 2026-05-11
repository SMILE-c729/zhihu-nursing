package com.zzyl.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI 会话配置。
 */
@Data
@Component
@ConfigurationProperties(prefix = "zzyl.ai.conversation")
public class AiConversationProperties
{
    /**
     * 会话在 Redis 中的缓存时长，单位：分钟
     */
    private Integer sessionTtlMinutes = 1440;

    private Integer maxContextMessageSize = 12;

    private Integer maxContextMessageChars = 1200;

    private Integer maxContextTotalChars = 6000;

    private Integer maxSessionMessageSize = 80;
}
