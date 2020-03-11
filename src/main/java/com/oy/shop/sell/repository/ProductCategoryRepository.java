package com.oy.shop.sell.repository;

import com.oy.shop.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/9 14:19
 * <ProductCategory , Integer>表示<entity的类型 , entity主键的类型>
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 注意方法名一定要是jpa自定义的
     *
     * @param categoryListType
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryListType);

    /**
     * 查找名称为categoryName的记录
     *
     * @return
     */
    ProductCategory findByCategoryName(String categoryName);
}
