package com.oy.shop.sell.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/21 10:49
 */
@Data
public class FtpDTO {
    /**
     * host FTP服务器ip
     */
    private String host;
    /**
     * port FTP服务器端口
     */
    private int port;
    /**
     * username FTP登录账号
     */
    private String username;
    /**
     * password FTP登录密码
     */
    private String password;
    /**
     * basePath FTP服务器基础目录,/home/ftpuser/images
     */
    private String basePath;
    /**
     * 访问图片时的基础url
     */
    private String baseUrl;
}
