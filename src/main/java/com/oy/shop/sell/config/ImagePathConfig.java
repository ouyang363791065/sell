package com.oy.shop.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/20 11:26
 */
@Data
@ConfigurationProperties(prefix = "ftp")
@Component
public class ImagePathConfig {
    /**
     * 主机地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * ftp用户名
     */
    private String userName;
    /**
     * ftp用户密码
     */
    private String passWord;
    /**
     * 文件在服务器端保存的主目录
     */
    private String basePath;
    /**
     * 访问图片时的基础url
     */
    private String baseUrl;
}
