package com.oy.shop.sell.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/12 8:40
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间 + 随机数
     *
     * @return
     */
    public static synchronized String genUniqueKeyStr() {
        return System.currentTimeMillis() + String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    /**
     * 生成唯一的主键
     * 格式: 年 + 月
     *
     * @return
     */
    public static synchronized Integer genUniqueKeyInt() {
        return Integer.valueOf(new SimpleDateFormat("MMdd")
                .format(new Date()) + (int) (Math.random() * 10));
    }
}
