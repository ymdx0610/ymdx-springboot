package com.ymdx.service;

import com.ymdx.pojo.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 14:18
 * @Version: 1.0
 **/
public interface UserService {
    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 分页查找用户
     */
    List<User> findUsers(int pageNum, int pageSize);
}
