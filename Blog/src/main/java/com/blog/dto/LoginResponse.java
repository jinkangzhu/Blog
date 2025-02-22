package com.blog.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String message;

    // Getters and Setters
}
