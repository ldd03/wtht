package com.ncwu.dao;

import com.ncwu.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: UserDao
 * Package: com.ncwu.dao
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:54
 * @Version 1.0
 */
@Mapper
public interface UserDao {
    @Select("select * from tb_user")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "user_status")
    })
    public List<User> getAll();

    @Select("select * from tb_user where user_id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "user_status")
    })
    public User getById(@Param("id") String id);
    

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "user_status")
    })
    @Select("select * from tb_user where user_id like #{userId}")
    public List<User> getByUserId(@Param("userId") String userId);

    @Update("update tb_user set name = #{name}, password = #{password}, role = #{role} ,user_status = #{status}" +
            " where user_id = #{userId}")
    public int update(User user);

    @Delete("delete from tb_user where user_id = #{id}")
    public int delete(@Param("id") int id);

    @Insert("insert into tb_user (user_id,name,password,role,user_status) " +
            "values(#{userId}, #{name}, #{password}, #{role}, #{status})")
    public int add(User user);
}
