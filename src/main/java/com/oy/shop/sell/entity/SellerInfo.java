package com.oy.shop.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/23 15:23
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}

