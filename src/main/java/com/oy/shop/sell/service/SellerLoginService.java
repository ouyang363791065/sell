package com.oy.shop.sell.service;

import com.oy.shop.sell.entity.SellerInfo;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/12 18:03
 */
public interface SellerLoginService {
    SellerInfo checkSeller(SellerInfo sellerInfo);
}
