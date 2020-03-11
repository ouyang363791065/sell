package com.oy.shop.sell.service.impl;

import com.oy.shop.sell.entity.SellerInfo;
import com.oy.shop.sell.repository.SellerInfoRepository;
import com.oy.shop.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    private SellerInfoRepository repository;

    @Autowired
    public SellerServiceImpl(SellerInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
