package com.blog.config;


import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public SnowflakeGenerator getSnowflakeGenerator(){
        return new SnowflakeGenerator();
    }
}
