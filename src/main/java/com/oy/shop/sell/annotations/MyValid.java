package com.oy.shop.sell.annotations;

import com.oy.shop.sell.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 在使用@MyValid的时候，会对category_type进行校验是否重复
 * @Author: feixi
 * @Date: 2020/1/22 19:56
 */
public class MyValid implements ConstraintValidator<MyConstraint, Object> {
    private ProductCategoryRepository repository;

    @Autowired
    public MyValid(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Object name, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findByCategoryName(name + "") == null;
    }
}
