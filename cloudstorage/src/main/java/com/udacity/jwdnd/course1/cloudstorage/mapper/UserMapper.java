package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
/*
 * UserMapper Interface für Dateneintrag
 * */
public interface UserMapper{

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty ="userId")
    int insert(User user);

    @Select("SELECT * FROM USERS WHERE username = #{username} LIMIT 1")
    User getUserByUsername(String username);

    @Delete("DELETE FROM USERS WHERE id = #{userId}")
    void deleteUser(int userId);

}