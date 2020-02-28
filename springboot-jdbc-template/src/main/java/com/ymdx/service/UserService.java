package com.ymdx.service;

import com.ymdx.dao.UserDao;
import com.ymdx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 13:57
 * @Version: 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(User user){
        userDao.addUser(user);
    }

}
