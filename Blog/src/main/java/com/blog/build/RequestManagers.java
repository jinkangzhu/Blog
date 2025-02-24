package com.blog.build;

import com.blog.pojo.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtBuilder;

public class RequestManagers {

    public static RequestManagerBuilder builder() {
        return new RequestManagerBuilder();
    }
}
