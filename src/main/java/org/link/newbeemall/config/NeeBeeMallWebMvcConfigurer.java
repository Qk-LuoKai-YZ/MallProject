package org.link.newbeemall.config;

import org.link.newbeemall.common.Constants;
import org.link.newbeemall.interceptor.AdminLoginInterceptor;
import org.link.newbeemall.interceptor.NewBeeMallCartNumberInterceptor;
import org.link.newbeemall.interceptor.NewBeeMallLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NeeBeeMallWebMvcConfigurer
 * Author:  7ink
 * Data:    2024/5/13 17:06
 * Description:配置拦截器，
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Configuration
public class NeeBeeMallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Autowired
    private NewBeeMallLoginInterceptor newBeeMallLoginInterceptor;

    @Autowired
    private NewBeeMallCartNumberInterceptor newBeeMallCartNumberInterceptor;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 后台登陆拦截
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");

        // 商城页面登陆拦截
        registry.addInterceptor(newBeeMallLoginInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .addPathPatterns("/goods/detail/**")
                .addPathPatterns("/shop-cart")
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/saveOrder")
                .addPathPatterns("/orders")
                .addPathPatterns("/orders/**")
                .addPathPatterns("/personal")
                .addPathPatterns("/personal/updateInfo")
                .addPathPatterns("/selectPayType")
                .addPathPatterns("/payPage");

        // 购物车中的数量统一处理
        registry.addInterceptor(newBeeMallCartNumberInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");
    }

    /**
     * 静态资源处理器
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
