package com.oy.shop.sell.service.impl;

import com.oy.shop.sell.entity.SellerInfo;
import com.oy.shop.sell.repository.SellerLoginRepository;
import com.oy.shop.sell.service.SellerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/13 15:44
 */
@Service
public class SellerLoginServiceImpl implements SellerLoginService {
    private SellerLoginRepository repository;

    @Autowired
    public SellerLoginServiceImpl(SellerLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public SellerInfo checkSeller(SellerInfo sellerInfo) {
        return repository.findByUsernameAndPassword(sellerInfo.getUsername(), sellerInfo.getPassword());
    }
}
