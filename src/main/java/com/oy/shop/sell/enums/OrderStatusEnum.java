package com.oy.shop.sell.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/11 9:33
 */
@Getter
public enum
OrderStatusEnum implements CodeEnum{
    NEW(0, "新下单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
