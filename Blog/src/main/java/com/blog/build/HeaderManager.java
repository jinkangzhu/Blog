package com.blog.build;

import io.jsonwebtoken.Header;

import java.util.Map;

public interface HeaderManager<T> extends Map<String, Object> {

    String USER_INFO = "userInfo";

    T getUserInfo();

    void setUserInfo(String var1);

    // 从 JwtBuilder 里面学的
    // public interface Header<T extends io.jsonwebtoken.Header<T>> extends Map<String, Object> {


}
