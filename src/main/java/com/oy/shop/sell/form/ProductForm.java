package com.oy.shop.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/19 12:24
 */
@Data
public class ProductForm {

    private String productId;

    @NotEmpty(message = "商品名称不能为空")
    private String productName;

    @NotNull(message = "商品单价不能为空")
    private BigDecimal productPrice;

    @NotNull(message = "商品库存不能为空")
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private String categoryType;
}
