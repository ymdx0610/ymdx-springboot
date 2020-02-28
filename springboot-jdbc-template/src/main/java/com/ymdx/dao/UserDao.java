package com.ymdx.dao;

import com.ymdx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 14:01
 * @Version: 1.0
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user){
        jdbcTemplate.update("insert into t_user(name,password,email,birthday) values(?,?,?,?)",
                new Object[]{user.getName(), user.getPassword(), user.getEmail(), user.getBirthday()});
    }

}
