package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO 换一个拦截器
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()  // 禁用 CSRF（如果是 REST API）
//                .authorizeRequests()
//                .antMatchers("/api/auth/login").permitAll()  // 登录接口允许公开访问
//                .anyRequest().authenticated()  // 其他接口需要认证
//                .and()
//                .formLogin().disable();  // 禁用默认的表单登录
    }

    // TODO 换加密算法
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 进行密码加密
    }
}
