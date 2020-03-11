package com.oy.shop.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: http请求返回的最外层对象
 * @Author: feixi
 * @Date: 2020/1/10 15:22
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 5560884988509714018L;

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;
}
