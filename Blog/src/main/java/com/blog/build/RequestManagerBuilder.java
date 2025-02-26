package com.blog.build;

import com.alibaba.fastjson.JSON;
import com.blog.pojo.User;
import com.blog.vo.RequestContextManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;


public class RequestManagerBuilder {

    private RequestContextManager requestContextManager;

    public RequestManagerBuilder() {
        this.requestContextManager = new RequestContextManager();
    }

    public RequestManagerBuilder buildUser(Claims claims) throws JsonProcessingException {
        LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>) claims.get("user");
        System.out.println(JSON.toJSONString(userMap));
        User user = new User();
        user.setId((String) userMap.get("id"));
        user.setUsername((String) userMap.get("username"));
        user.setPassword((String) userMap.get("password"));
        user.setEmail((String) userMap.get("email"));
        user.setPhone((String) userMap.get("phone"));
        user.setStatus((String) userMap.get("status"));
        user.setCreateBy((String) userMap.get("createBy"));
        user.setCreateDate((LocalDateTime) userMap.get("createDate"));
        user.setUpdateBy((String) userMap.get("updateBy"));
        user.setUpdateDate((LocalDateTime) userMap.get("updateDate"));
        user.setDeleteFlag((String) userMap.get("deleteFlag"));
        this.requestContextManager.setUserInfo(user);
        return this;
    }

    public RequestContextManager getRequestContextManager() {
        return this.requestContextManager;
    }
}
