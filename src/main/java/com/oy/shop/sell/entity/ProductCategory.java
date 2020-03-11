package com.oy.shop.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 类目，SpringDataJpa帮我们干了从驼峰式类名到下划线表名的映射
 * @Author: feixi
 * @Date: 2020/1/9 14:14
 * @Entity 数据库映射成对象
 * @GeneratedValue 自增
 * @Id 主键
 * @DynamicUpdate 自动更新（动态更新）updatetime
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /**
     * 类目id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
