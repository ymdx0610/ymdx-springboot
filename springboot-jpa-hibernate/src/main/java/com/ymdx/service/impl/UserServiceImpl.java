package com.ymdx.service.impl;

import com.ymdx.dao.UserDao;
import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 07:48
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findUser(Long id) {
        return userDao.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUser(Long id) {
        userDao.deleteById(id);
    }

}
