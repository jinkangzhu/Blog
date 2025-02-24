package com.blog.service.impl;

import com.blog.dto.LoginRequest;
import com.blog.dto.LoginResponse;
import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import com.blog.properties.JwtProperties;
import com.blog.service.LoginService;
import com.blog.utils.JwtUtil;
import com.sun.xml.internal.bind.v2.TODO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class LoginServiceImpl implements LoginService {

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
//        // 2. 验证密码
//        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            throw new RuntimeException("密码错误");
//        }

        // 2. 验证密码
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 3. 生成 token
        String token = generateToken(user);

        // 4. 返回登录响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("登录成功");

        return response;
    }


    public void register(User user) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(user.getUsername()) != null) {
            // throw new Exception("Username already exists.");
            throw new RuntimeException("用户名用过了，亲~");
        }

//        // 加密用户密码
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        user.setPassword(user.getPassword());

        // TODO 改成雪花算法
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());

        // 保存用户到数据库
        userMapper.insertUser(user);
    }

    // 生成 Token（简化版，实际项目中使用 JWT）
    private String generateToken(User user) {

//        //TODO 在实际项目中，你应该使用 JWT（JSON Web Token）生成加密的 token
//        return "simple_token_" + user.getUsername();  // 简化示例
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);
        return JwtUtil.createJWT(jwtProperties.getSecretKey(), 3600, claims);

    }
}
