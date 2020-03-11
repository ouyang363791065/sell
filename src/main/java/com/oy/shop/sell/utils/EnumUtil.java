package com.oy.shop.sell.utils;

import com.oy.shop.sell.enums.CodeEnum;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/20 16:01
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
