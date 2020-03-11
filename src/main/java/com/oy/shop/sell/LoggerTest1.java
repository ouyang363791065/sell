package com.oy.shop.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/8 21:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest1 {
    /**
     * 指定获取当前类的日志，因为日志会输出：类名+：+报错信息，这里确定下是这个类的日志信息。
     */
    private final Logger logger = LoggerFactory.getLogger(LoggerTest1.class);

    @Test
    public void test(){
        /**
         * 在Level类里面定义了日志级别，默认为info
         * ERROR(40, "ERROR"),
         * WARN(30, "WARN"),
         * INFO(20, "INFO"),
         * DEBUG(10, "DEBUG"),
         * TRACE(0, "TRACE");
         */
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");



        JComboBox jComboBox = new JComboBox();
    }
}
