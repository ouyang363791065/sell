package com.oy.shop.sell.exception;

import com.oy.shop.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Description: 自定义非检查异常，在程序发生不被预期的结果就手动抛出
 * @Author: feixi
 * @Date: 2020/1/12 8:13
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        //message传入父类构造方法
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
