package com.oy.shop.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 商品详情
 * @Author: feixi
 * @Date: 2020/1/10 15:31
 */
@Data
public class ProductInfoVO implements Serializable {


    private static final long serialVersionUID = -1047957436597588176L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private  String productDescription;

    @JsonProperty("icon")
    private  String productIcon;
}
