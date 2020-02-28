package com.ymdx.dao;

import com.ymdx.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 07:48
 * @Version: 1.0
 **/
public interface UserDao extends JpaRepository<User, Long> {
}
