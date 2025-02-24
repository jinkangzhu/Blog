package com.blog.vo;

import com.blog.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestContextManager {
    private User userInfo;

}
