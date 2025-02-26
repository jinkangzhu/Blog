package com.blog.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String message;
// TODO 返回给前端统一的Result
}
