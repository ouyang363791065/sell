package com.oy.shop.sell.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/11 9:36
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
