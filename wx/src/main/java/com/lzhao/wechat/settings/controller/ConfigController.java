package com.lzhao.wechat.settings.controller;

import com.lzhao.wechat.settings.properties.WeChatProperties;
import com.lzhao.wechat.util.MessageUtil;
import com.lzhao.wechat.util.SignUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @PostMapping(value = "securitycheck")
    public void getMessage(HttpServletRequest request) {
        try
        {
            Map<String, String> message = MessageUtil.parseXml(request);
            logger.info(message);
        } catch (Exception e)
        {
            logger.error(e);
        }
    }

}
