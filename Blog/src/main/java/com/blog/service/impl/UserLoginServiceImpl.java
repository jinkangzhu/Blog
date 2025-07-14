package com.blog.service.impl;

import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.exception.BusinessMsgEnum;
import com.blog.mapper.UserMapper;
import com.blog.entity.User;
import com.blog.properties.JwtProperties;
import com.blog.service.UserLoginService;
import com.blog.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userMapper.findByUsername(loginRequest.getUsername());
        BusinessMsgEnum.USER_NOT_FOUND.assertNotNull(user);

        //TODO 这里是密文存、密文校验，先不搞这个
        BusinessMsgEnum.ERROR_PASSWORD.assertIsTrue(user.getPassword().equals(loginRequest.getPassword()));

        String token = generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }


    public void register(User user) {
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
