package com.oy.shop.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/1/19 14:51
 */
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
