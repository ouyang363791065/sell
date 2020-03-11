package com.oy.shop.sell.repository;

import com.oy.shop.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/11 9:42
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String openId);
}
