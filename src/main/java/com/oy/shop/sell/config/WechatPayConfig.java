package com.oy.shop.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/19 14:20
 */
@Component
public class WechatPayConfig {
    private WechatAccountConfig config;

    @Autowired
    public WechatPayConfig(WechatAccountConfig config) {
        this.config = config;
    }

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(getWxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config getWxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(config.getMpAppId());
        wxPayH5Config.setAppSecret(config.getMpAppSecret());
        wxPayH5Config.setMchId(config.getMchId());
        wxPayH5Config.setMchKey(config.getMchKey());
        wxPayH5Config.setKeyPath(config.getKeyPath());
        wxPayH5Config.setNotifyUrl(config.getNotifyUrl());
        return wxPayH5Config;
    }
}
