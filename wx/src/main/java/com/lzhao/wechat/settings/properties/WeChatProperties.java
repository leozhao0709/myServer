package com.lzhao.wechat.settings.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lzhao on 5/28/17.
 */
@Component(value = "weChatProperties")
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {

    private String token;
    private String encodingAESKey;
    private String appID;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
