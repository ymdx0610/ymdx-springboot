package com.ymdx.service;

import com.ymdx.pojo.User;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 11:35
 * @Version: 1.0
 **/
public interface UserService {

    User findUserByName(String name);

    void saveUser(User user);

    String findRedis();

}
