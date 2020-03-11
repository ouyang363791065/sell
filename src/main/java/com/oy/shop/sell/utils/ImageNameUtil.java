package com.oy.shop.sell.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/21 15:36
 */
public class ImageNameUtil {
    public static String getImageName(MultipartFile file){
        String name = file.getOriginalFilename();//上传文件的真实名称
        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        String hash = UUID.randomUUID().toString();
        return hash + suffixName;
    }
}
