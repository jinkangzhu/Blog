package com.blog.service.impl;

import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.exception.BusinessMsgEnum;
import com.blog.mapper.UserMapper;
import com.blog.entity.User;
import com.blog.properties.JwtProperties;
import com.blog.service.UserLoginService;
import com.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 1. 查找用户
        User user = userMapper.findByUsername(loginRequest.getUsername());
        if (user == null) {
            // TODO 全局异常处理器、异常类、返回统一类
            throw new RuntimeException("用户名不存在");
        }

        //TODO 这里是密文存、密文校验，先不搞这个
        // 2. 验证密码
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 3. 生成 token
        String token = generateToken(user);

        // 4. 返回登录响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }


    public void register(User user) {
        // 检查用户名是否已存在
        BusinessMsgEnum.USER_DUPLICATE.assertNull(userMapper.findByUsername(user.getUsername()));

        // TODO 加密用户密码
        user.setPassword(user.getPassword());

        // TODO 改成雪花算法
        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        user.setCreateUserInfo(uuid);


        // 保存用户到数据库
        userMapper.insert(user);
    }

    // 生成 Token
    private String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);
        return JwtUtil.createJWT(jwtProperties.getSecretKey(), 3600000, claims);

    }
}
