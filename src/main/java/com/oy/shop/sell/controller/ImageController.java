package com.oy.shop.sell.controller;

import com.oy.shop.sell.VO.ResultVO;
import com.oy.shop.sell.config.FtpConfig;
import com.oy.shop.sell.utils.FtpUtil;
import com.oy.shop.sell.utils.ImageNameUtil;
import com.oy.shop.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/19 21:47
 */
@RestController
@Slf4j
@RequestMapping("/image")
public class ImageController {
    private final FtpConfig ftpConfig;

    @Autowired
    public ImageController(FtpConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file_data") MultipartFile multipartFile) throws IOException {
        //获取上传的io流
        InputStream input = multipartFile.getInputStream();
        //生成文件在服务器端存储的子目录
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        //生成文件名
        String filename = ImageNameUtil.getImageName(multipartFile);
        boolean result = FtpUtil.uploadFile(ftpConfig.getFtpDto(), filePath, filename, input);
        if (!result) {
            log.info("图片上传失败");
            return ResultVOUtil.error(500, "图片上传失败");
        }
        Map map = new HashMap<>();
        map.put("url", ftpConfig.getFtpDto().getBaseUrl() + filePath + "/" + filename);
        log.info("图片上传成功");
        return ResultVOUtil.success(map);
    }
}
