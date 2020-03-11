package com.oy.shop.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableCaching    //缓存的使用
 */
@SpringBootApplication
@EnableScheduling  //开启定时
@EnableCaching    //缓存的使用
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }

}
