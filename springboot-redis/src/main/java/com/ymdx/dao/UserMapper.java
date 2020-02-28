package com.ymdx.dao;

import com.ymdx.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 11:38
 * @Version: 1.0
 **/
@Repository
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    @Select("select * from t_user where name=#{name}")
    User findUserByName(@Param("name") String name);

    /**
     * 添加用户
     * @param name
     * @param password
     */
    @Insert("insert into t_user(name,password) values(#{name},#{password})")
    void addUser(@Param("name") String name, @Param("password") String password);

}
