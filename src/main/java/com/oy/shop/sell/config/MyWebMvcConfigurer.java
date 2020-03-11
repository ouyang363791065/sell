package com.oy.shop.sell.config;

import com.oy.shop.sell.component.LoginHandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: feixi
 * @Date: 2020/2/15 14:54
 */
@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 对于管理页面，放行注册
         * 放行 sell/seller/common/login、sell/seller/login、/buyer/**
         * 其他都拦截
         */
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/common/*", "/seller/login", "/buyer/**","/seller/logout","/wechat/**")
                .excludePathPatterns("/images/**","/css/**","/js/**","/fonts/**","/mp3/**");
    }

    /**
     * addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
     * registry.addViewController("请求路径").setViewName("请求页面文件路径")
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
    }
}
