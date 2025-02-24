package com.blog.build;

import com.blog.context.RequestContext;
import com.blog.pojo.User;
import com.blog.vo.RequestContextManager;
import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;


public class RequestManagerBuilder {

    private RequestContextManager requestContextManager;

    public RequestManagerBuilder() {
        this.requestContextManager = new RequestContextManager();
    }

    public RequestManagerBuilder buildUser(Claims claims) {

        User user = (User) claims.get("user");

        this.requestContextManager.setUserInfo(user);
        return this;
    }

    public RequestContextManager getRequestContextManager() {
        return this.requestContextManager;
    }
}
