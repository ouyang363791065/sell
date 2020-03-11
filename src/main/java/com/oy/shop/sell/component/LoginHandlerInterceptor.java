package com.oy.shop.sell.component;

import com.oy.shop.sell.entity.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/15 14:46
 */
@Component
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 调用时间：Controller方法处理之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        SellerInfo seller = (SellerInfo)request.getSession().getAttribute("seller");
        if (seller == null){
            request.setAttribute("msg", "无权限请先登录");
            //获取request转发到login
            request.getRequestDispatcher("/common/login").forward(request, response);
            return false;
        }
        log.info("preHandle----" + seller.getUsername() + " ::: " + request.getRequestURL());
        return true;
    }

    /**
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        SellerInfo seller = (SellerInfo)request.getSession().getAttribute("seller");
        log.info("postHandle----" + seller.getUsername() + " ::: " + request.getRequestURL());
    }

    /**
     * 调用时间：DispatcherServlet进行视图的渲染之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        SellerInfo seller = (SellerInfo)request.getSession().getAttribute("seller");
        log.info("afterCompletion----" + seller.getUsername() + " ::: " + request.getRequestURL());
    }
}
