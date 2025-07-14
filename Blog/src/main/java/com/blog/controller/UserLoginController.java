package com.blog.controller;

import com.blog.converter.UserDTOToUserConverter;
import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.exception.BusinessMsgEnum;
import com.blog.exception.ResponseResult;
import com.blog.entity.User;
import com.blog.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = UserDTOToUserConverter.INSTANCE.convert(loginRequest);
        LoginResponse response = userLoginService.login(loginRequest);
        return ResponseResult.success("登录成功！", response);  // 返回成功的登录响应
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody User user) {
        try {
            // 注册用户并返回结果
            userLoginService.register(user);
            return ResponseResult.success("恭喜你，注册成功！");
        } catch (Exception e) {
            return ResponseResult.error("500", "LoginFailed", "注册失败：" + e.getMessage());
        }
    }
}
