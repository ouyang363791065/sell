package com.oy.shop.sell.form;

import com.oy.shop.sell.annotations.MyConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/19 12:24
 */
@Data
public class CategoryForm {
    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 类目名字.
     *
     * @MyConstraint 自定义的注解，校验类目名字，使它不重复
     */
    @MyConstraint(message = "类目名称已存在")
    private String categoryName;
}
