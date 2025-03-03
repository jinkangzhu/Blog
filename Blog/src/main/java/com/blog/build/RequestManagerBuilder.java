package com.blog.build;

import com.blog.entity.User;
import com.blog.vo.RequestContextManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Slf4j
public class RequestManagerBuilder {

    private RequestContextManager requestContextManager;

    public RequestManagerBuilder() {
        this.requestContextManager = new RequestContextManager();
    }

    public RequestManagerBuilder buildUser(Claims claims) throws JsonProcessingException {
        LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>) claims.get("user");
        User user = new User();
        user.setId((String) userMap.get("id"));
        user.setUsername((String) userMap.get("username"));
        log.info("欢迎" + user.getUsername() + "~");
        user.setPassword((String) userMap.get("password"));
        user.setEmail((String) userMap.get("email"));
        user.setPhone((String) userMap.get("phone"));
        user.setStatus((String) userMap.get("status"));
        user.setCreatedBy((String) userMap.get("createBy"));
        user.setCreatedDate((LocalDateTime) userMap.get("createDate"));
        user.setUpdatedBy((String) userMap.get("updateBy"));
        user.setUpdatedDate((LocalDateTime) userMap.get("updateDate"));
        user.setDeletedFlag((String) userMap.get("deleteFlag"));
        this.requestContextManager.setUserInfo(user);
        return this;
    }

    public RequestContextManager getRequestContextManager() {
        return this.requestContextManager;
    }
}
