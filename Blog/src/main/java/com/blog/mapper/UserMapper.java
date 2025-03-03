package com.blog.mapper;


import com.blog.entity.User;
import java.util.List;

public interface UserMapper {

    User findById(String id);

    List<User> findAll();

    void insert(User user);

    void update(User user);

    void delete(String id);

    User findByUsername(String username);
}