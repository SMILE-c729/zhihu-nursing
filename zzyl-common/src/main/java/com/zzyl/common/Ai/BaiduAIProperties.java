package com.zzyl.common.Ai;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "baidu.ai")
public class BaiduAIProperties {
    private String apiKey;
    private String model;
    private String baseUrl;
}
