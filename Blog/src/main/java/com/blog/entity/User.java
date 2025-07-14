package com.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class User extends BaseEntity{
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;
}
