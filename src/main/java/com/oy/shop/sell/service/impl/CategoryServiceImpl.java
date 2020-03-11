package com.oy.shop.sell.service.impl;

import com.oy.shop.sell.entity.ProductCategory;
import com.oy.shop.sell.repository.ProductCategoryRepository;
import com.oy.shop.sell.service.CategoryService;
import com.oy.shop.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/10 14:08
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private ProductCategoryRepository repository;

    @Autowired
    public void setRepository(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).isPresent() ? repository.findById(categoryId).get() : null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        productCategory.setCategoryType(KeyUtil.genUniqueKeyInt());
        return repository.save(productCategory);
    }

    @Override
    public ProductCategory findByCategoryName(String name) {
        return repository.findByCategoryName(name);
    }
}
