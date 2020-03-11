package com.oy.shop.sell.service;

import com.oy.shop.sell.entity.ProductCategory;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/10 14:05
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory findByCategoryName(String name);
}
