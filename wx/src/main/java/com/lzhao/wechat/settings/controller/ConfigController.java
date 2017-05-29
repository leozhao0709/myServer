package com.lzhao.wechat.settings.controller;

import com.lzhao.wechat.settings.properties.WeChatProperties;
import com.lzhao.wechat.util.SignUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lzhao on 5/29/17.
 */
@RestController
@RequestMapping(value = "config")
public class ConfigController {

    private static Logger logger = Logger.getLogger(ConfigController.class);

    @Resource(name = "weChatProperties")
    private WeChatProperties weChatProperties;

    @GetMapping(value = "securitycheck")
    public String securityCheck(
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr)
    {

        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce, weChatProperties.getToken())) {
                return echostr;
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e, e);
        }

        return null;
    }

}
