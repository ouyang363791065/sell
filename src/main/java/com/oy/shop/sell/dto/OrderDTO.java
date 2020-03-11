package com.oy.shop.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oy.shop.sell.entity.OrderDetail;
import com.oy.shop.sell.enums.OrderStatusEnum;
import com.oy.shop.sell.enums.PayStatusEnum;
import com.oy.shop.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: data transfer object DTO 订单DTO对象，一个商品订单包含多个订单项
 * @Author: feixi
 * @Date: 2020/1/11 19:54
 */
@Data
public class OrderDTO {
    /**
     * 订单id.
     */
    @Id
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 多个订单项
     */
    private List<OrderDetail> orderDetailList;

    /**
     * 获取OrderStatusEnum
     *
     * @return
     * @JsonIgnore 将对象转换为json格式之后就会忽略掉这个字段
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    /**
     * 获取PayStatusEnum
     *
     * @return
     */
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
