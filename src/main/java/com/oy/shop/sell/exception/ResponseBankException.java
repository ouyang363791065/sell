package com.oy.shop.sell.exception;

import com.oy.shop.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/26 13:54
 */
@Getter
public class ResponseBankException extends RuntimeException {
    private Integer code;

    public ResponseBankException(ResultEnum resultEnum) {
        //message传入父类构造方法
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ResponseBankException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
