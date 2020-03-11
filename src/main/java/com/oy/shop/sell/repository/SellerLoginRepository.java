package com.oy.shop.sell.repository;

import com.oy.shop.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/13 15:45
 */
public interface SellerLoginRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByUsernameAndPassword(String username, String password);
}
