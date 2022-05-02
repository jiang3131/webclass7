package com.example.webclass.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bosai")
public class BosProperties {
    String ACCESS_KEY_ID;
    String SECRET_ACCESS_KEY;
    String ENDPOINT;

    public String getACCESS_KEY_ID() {
        return ACCESS_KEY_ID;
    }

    public void setACCESS_KEY_ID(String ACCESS_KEY_ID) {
        this.ACCESS_KEY_ID = ACCESS_KEY_ID;
    }

    public String getSECRET_ACCESS_KEY() {
        return SECRET_ACCESS_KEY;
    }

    public void setSECRET_ACCESS_KEY(String SECRET_ACCESS_KEY) {
        this.SECRET_ACCESS_KEY = SECRET_ACCESS_KEY;
    }

    public String getENDPOINT() {
        return ENDPOINT;
    }

    public void setENDPOINT(String ENDPOINT) {
        this.ENDPOINT = ENDPOINT;
    }
}
