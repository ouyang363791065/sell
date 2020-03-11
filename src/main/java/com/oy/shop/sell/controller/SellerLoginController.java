package com.oy.shop.sell.controller;

import com.oy.shop.sell.entity.SellerInfo;
import com.oy.shop.sell.service.SellerLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/12 17:57
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerLoginController {
    private SellerLoginService service;

    @Autowired
    public SellerLoginController(SellerLoginService service) {
        this.service = service;
    }

    /**
     * redirect:/ xxx  会直接调用xxx的controller
     *
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              Map<String, Object> map,
                              HttpSession session) {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername(username);
        sellerInfo.setPassword(password);
        SellerInfo check = service.checkSeller(sellerInfo);
        if (check == null) {
            log.info("卖家用户名密码输入错误");
            //清空session
            session.invalidate();
            map.put("msg", "登入失败");
            map.put("url", "/sell/common/login");
            return new ModelAndView("common/error", map);
        }
        log.info("卖家" + username + "登入成功");
        session.setAttribute("seller", check);
        map.put("msg", "登入成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/logout")
    public ModelAndView logout(Map<String, Object> map,
                               HttpSession session) {
        SellerInfo check = (SellerInfo) session.getAttribute("seller");
        if (check == null) {
            map.put("msg", "非法请求");
            map.put("url", "/sell/common/login");
            return new ModelAndView("common/error", map);
        }
        log.info("卖家" + check.getUsername() + "登出成功");
        session.invalidate();
        map.put("msg", "登出成功，Bye！");
        map.put("url", "/sell/common/login");
        return new ModelAndView("common/success", map);
    }
}
