package com.blog.service;

import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.pojo.User;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);

    void register(User user);
}
