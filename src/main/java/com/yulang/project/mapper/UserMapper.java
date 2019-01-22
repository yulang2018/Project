package com.yulang.project.mapper;

import com.yulang.project.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where pkid=#{pkid}")
    public User getUserById(@Param("pkid")Integer pkid);

    @Select("select * from user where phone=#{phone}")
    public User getUserByPhone(String phone);

    @Insert("insert into user(id,nickname,password,salt,head,register_date,last_login_date,login_count,phone) values(#{id},#{nickname},#{password},#{salt},#{head},#{registerDate},#{lastLoginDate},#{loginCount},#{phone}) ")
    public void insertUser(User user);
}
