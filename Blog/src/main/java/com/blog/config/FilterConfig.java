package com.blog.config;

import com.blog.filter.JwtRequestFilter;
import com.blog.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FilterConfig {
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//    @Bean
//    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilter() {
//        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
//        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
//        jwtRequestFilter.setJwtProperties(jwtProperties);
//        registrationBean.setFilter(jwtRequestFilter);
//
//        // 设置过滤器拦截的路径（例如 "/api/*"）
//        registrationBean.addUrlPatterns("/api/*");  // 拦截所有以 "/api/" 开头的路径
//
//        // 配置过滤器的优先级，数值越低，优先级越高
//        registrationBean.setOrder(1); // 1表示优先级较高
//
//        // 排除登录和注册路径（可以配置一个排除路径）
//        registrationBean.addInitParameter("excludeUrls", "/api/auth/login,/api/auth/register");
//
//        return registrationBean;
//    }
//}