package com.example.media_base.mapper;

import com.example.media_base.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where email=#{email}")
    User findByEmail(String email);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Insert("insert into user (username, password, email, create_time, update_time)" +
            " values (#{username}, #{encryptedPassword}, #{email}, now(), now())")
    void addWithName(String username, String encryptedPassword, String email);

    @Insert("insert into user (password, email, create_time, update_time)" +
            " values (#{encryptedPassword}, #{email}, now(), now())")
    void add(String encryptedPassword, String email);

    @Update("update user set username = #{username} where id = #{id}")
    void updateName(Integer id, String username);

    @Update("update user set username = #{username}, update_time = now() where id = #{id}")
    void update(User user);

    @Update("update user set avatar = #{avatar}, update_time = now() where id = #{id}")
    void updateAvatar(Integer id, String avatar);

    @Update("update user set password = #{password}, update_time = now() where id = #{id}")
    void updatePwd(Integer id, String password);

    @Select("select user_id from role where user_id = #{id} and role = 'ADMIN'")
    List<Integer> getAdminRole(Integer id);
}
