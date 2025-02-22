package com.blog.mapper;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM tpl_user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO tpl_user (id, username, password, email, phone, status, create_by, create_date, update_by, update_date, delete_flag) " +
            "VALUES (#{id}, #{username}, #{password}, #{email}, #{phone}, #{status}, #{createBy}, #{createDate}, #{updateBy}, #{updateDate}, #{deleteFlag})")
    void insertUser(User user);

    @Update("UPDATE tpl_user SET password = #{password}, update_by = #{updateBy}, update_date = #{updateDate} WHERE id = #{id}")
    void updateUser(User user);
}
