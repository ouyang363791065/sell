package com.oy.shop.sell.handler;

import com.oy.shop.sell.VO.ResultVO;
import com.oy.shop.sell.exception.ResponseBankException;
import com.oy.shop.sell.exception.SellException;
import com.oy.shop.sell.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description: 异常统一处理，使得异常不出现在后台控制台出现，对异常进行统一处理，
 * 然后返回指定数据给前端(内容@ResponseBody+状态码@ResponseStatus)
 * @Author: feixi
 * @Date: 2020/1/26 12:56
 */
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handlerResponseBankException(ResponseBankException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
