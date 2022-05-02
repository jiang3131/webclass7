package com.example.webclass.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 百度ai平台应用信息配置表
 */
@Configuration
@ConfigurationProperties(prefix = "baiduai")
public class BaiduAiProperties {
    String appId;
    String apiKey;
    String secretKey;

    public String getAppId(){
        return appId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
