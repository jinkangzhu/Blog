package com.blog.pojo;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;


@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;
    private String createBy;
    private LocalDateTime createDate;
    private String updateBy;
    private LocalDateTime updateDate;
    private String deleteFlag;
}
