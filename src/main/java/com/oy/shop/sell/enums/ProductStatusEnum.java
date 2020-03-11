package com.oy.shop.sell.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/9 19:52
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
