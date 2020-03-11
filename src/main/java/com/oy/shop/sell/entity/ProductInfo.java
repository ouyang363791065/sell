package com.oy.shop.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oy.shop.sell.enums.ProductStatusEnum;
import com.oy.shop.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/9 19:50
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = 8192307768393499612L;

    @Id
    private String productId;

    /**
     * 名字.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 描述.
     */
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;

    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
