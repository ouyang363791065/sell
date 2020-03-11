package com.oy.shop.sell.service;

import com.oy.shop.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/11 10:05
 */
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询所有的订单列表
     */
    Page<OrderDTO> findList(Pageable pageable);

    /**
     * 根据状态码查找相应的订单
     *
     * @return
     */
    List<OrderDTO> findListByOrderStatus(OrderDTO orderDTO);
}
