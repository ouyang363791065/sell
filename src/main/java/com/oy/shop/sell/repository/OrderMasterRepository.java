package com.oy.shop.sell.repository;

import com.oy.shop.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/11 9:39
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    /**
     * 根据openid查询订单
     *
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

    /**
     * 根据状态码查找相应的订单
     *
     * @param orderStatus
     * @return
     */
    List<OrderMaster> findByOrderStatus(Integer orderStatus);
}
