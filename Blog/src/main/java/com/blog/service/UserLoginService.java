package com.blog.service;

import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.entity.User;

public interface UserLoginService {
    LoginResponse login(LoginRequest loginRequest);

    void register(User user);
}
