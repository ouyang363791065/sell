package com.oy.shop.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Description: 订单项，就是一种商品的订单项，包含这种商品下了多少件，一共多少钱
 * @Author: feixi
 * @Date: 2020/1/11 9:37
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;

    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 商品id.
     */
    private String productId;

    /**
     * 商品名称.
     */
    private String productName;

    /**
     * 商品单价.
     */
    private BigDecimal productPrice;

    /**
     * 商品数量.
     */
    private Integer productQuantity;

    /**
     * 商品小图.
     */
    private String productIcon;
}
