package com.oy.shop.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/8 21:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//省略了使用LoggerFacory获取Logger的步骤，引用为log
@Slf4j
public class LoggerTest2 {
    @Test
    public void test() {
        log.debug("debug");
        String name = "zhangsan";
        String password = "123";
        log.info("name: {},password: {}", name, password);
    }
}
