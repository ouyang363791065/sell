package com.oy.shop.sell.service.impl;

import com.oy.shop.sell.dto.CartDTO;
import com.oy.shop.sell.entity.ProductInfo;
import com.oy.shop.sell.enums.ProductStatusEnum;
import com.oy.shop.sell.enums.ResultEnum;
import com.oy.shop.sell.exception.SellException;
import com.oy.shop.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import com.oy.shop.sell.repository.ProductInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/10 14:18
 */
@Service
public class ProductServiceImpl implements ProductService {
    private ProductInfoRepository repository;

    @Autowired
    public void setRepository(ProductInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).isPresent() ? repository.findById(productId).get() : null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> entity = repository.findById(cartDTO.getProductId());
            if (!entity.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            } else {
                ProductInfo productInfo = entity.get();
                int temp = productInfo.getProductStock() - cartDTO.getProductQuantity();
                if (temp < 0) {
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                } else {
                    productInfo.setProductStock(temp);
                }
                repository.save(productInfo);
            }
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> entity = repository.findById(cartDTO.getProductId());
            if (!entity.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            } else {
                ProductInfo productInfo = entity.get();
                int temp = productInfo.getProductStock() + cartDTO.getProductQuantity();
                productInfo.setProductStock(temp);
                repository.save(productInfo);
            }
        }
    }
}
