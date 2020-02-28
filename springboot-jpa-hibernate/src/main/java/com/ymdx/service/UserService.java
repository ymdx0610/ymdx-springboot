package com.ymdx.service;

import com.ymdx.pojo.User;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 07:45
 * @Version: 1.0
 **/
public interface UserService {

    void addUser(User user);

    User findUser(Long id);

    void delUser(Long id);

}
