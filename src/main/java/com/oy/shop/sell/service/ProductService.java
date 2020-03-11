package com.oy.shop.sell.service;

import com.oy.shop.sell.dto.CartDTO;
import com.oy.shop.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/10 14:04
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有上架了的商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架商品
    ProductInfo onSale(String productId);

    //商品下架
    ProductInfo offSale(String productId);
}
