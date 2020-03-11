package com.oy.shop.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/12 17:38
 */
@Controller
public class PageController {
    @RequestMapping("/common/{page}")
    public String test(@PathVariable String page) {
        return "/common/" + page;
    }
}
