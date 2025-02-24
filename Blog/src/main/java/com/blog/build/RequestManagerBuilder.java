package com.blog.build;

import com.blog.context.RequestContext;
import com.blog.pojo.User;
import com.blog.vo.RequestContextManager;
import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;


public class RequestManagerBuilder {

    private RequestContextManager requestContextManager;

//    public RequestManagerBuilder() {
//        this.requestContextManager = new RequestContextManager();
//    }

    public RequestManagerBuilder buildUser(Claims claims) {

        User user = new User();
        user.setId(claims.get("id", String.class));
        user.setUsername(claims.get("username", String.class));
        user.setPassword(claims.get("password", String.class));
        user.setEmail(claims.get("email", String.class));
        user.setPhone(claims.get("phone", String.class));
        user.setStatus(claims.get("status", String.class));
        user.setCreateBy(claims.get("createBy", String.class));
        user.setCreateDate(claims.get("createDate", LocalDateTime.class));
        user.setUpdateBy(claims.get("updateBy", String.class));
        user.setUpdateDate(claims.get("updateDate", LocalDateTime.class));
        user.setDeleteFlag(claims.get("deleteFlag", String.class));
        this.requestContextManager.setUserInfo(user);
        return this;
    }

    public RequestContextManager getRequestContextManager() {
        return this.requestContextManager;
    }
}
