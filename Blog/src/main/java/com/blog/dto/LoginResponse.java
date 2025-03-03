package com.blog.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
// TODO 返回给前端统一的Result
}
