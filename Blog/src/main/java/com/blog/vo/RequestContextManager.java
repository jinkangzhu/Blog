package com.blog.vo;

import com.blog.entity.User;
import lombok.Data;

@Data
public class RequestContextManager {
    private User userInfo;

    public User getUserInfo() {
        return userInfo;
    }
}
