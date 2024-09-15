package com.gub.mapper;

import com.gub.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;


@Mapper
public interface UserMapper {


    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time) values (#{username}, #{password}, now(), now())")
    void add(String username, String password);

    @Update("update user set nickname = #{nickname}, email = #{email}, user_pic = #{userPic}, update_time = now() where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{url}, update_time = now() where id = #{id}")
    void updateAvatar(String url, Integer id);

    @Update("update user set password = #{newPassword}, update_time = now() where id = #{id}")
    void updatePwd(String newPassword, Integer id);
}
