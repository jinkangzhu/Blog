package com.blog.mapper;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from test_user where id = #{id}")
    public User findUserById(Integer id);


}
