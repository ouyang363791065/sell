package com.oy.shop.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 商品(包含类目)
 * @Author: feixi
 * @Date: 2020/1/10 15:25
 * @JsonProperty("name") private String categoryName;
 * 因为返回给前端的时候直接是categoryName: xxx，
 * 加上注解可以转换为括号内的内容。
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 2035372497321789508L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
