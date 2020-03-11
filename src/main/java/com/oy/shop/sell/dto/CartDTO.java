package com.oy.shop.sell.dto;

import lombok.Data;

/**
 * @Description: 购物车
 * @Author: feixi
 * @Date: 2020/1/12 9:11
 */
@Data
public class CartDTO {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
