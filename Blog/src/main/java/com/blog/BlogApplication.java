package com.blog;

import com.blog.exception.BusinessMsgEnum;
import com.blog.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.blog.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        try {
            int a =1/0;
            String userId ="13";
            User user =null;
        } catch (Exception e) {
            log.error("dawdwad:{}",e.getMessage());
            BusinessMsgEnum.SYSTEM_ERROR.assertNull(e, e.getMessage());
        }
//
//        SpringApplication.run(BlogApplication.class, args);
//        System.out.println("(*^▽^*) blog启动成功！");
//        log.debug("debug~~");
//        log.info("info~~");
//        log.warn("warn~~");
//        log.error("error~~");
    }

}
