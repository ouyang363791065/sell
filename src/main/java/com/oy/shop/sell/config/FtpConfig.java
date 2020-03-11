package com.oy.shop.sell.config;

import com.oy.shop.sell.dto.FtpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/20 11:43
 */
@Component
public class FtpConfig {
    private ImagePathConfig imagePathConfig;

    @Autowired
    public FtpConfig(ImagePathConfig imagePathConfig) {
        this.imagePathConfig = imagePathConfig;
    }

    @Bean
    public FtpDTO getFtpDto() {
        FtpDTO ftpDTO = new FtpDTO();
        ftpDTO.setHost(imagePathConfig.getHost());
        ftpDTO.setPort(imagePathConfig.getPort());
        ftpDTO.setUsername(imagePathConfig.getUserName());
        ftpDTO.setPassword(imagePathConfig.getPassWord());
        ftpDTO.setBasePath(imagePathConfig.getBasePath());
        ftpDTO.setBaseUrl(imagePathConfig.getBaseUrl());
        return ftpDTO;
    }
}
