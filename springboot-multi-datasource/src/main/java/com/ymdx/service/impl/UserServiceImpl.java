package com.ymdx.service.impl;

import com.ymdx.dao.db1.UserMapper;
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
 * @Date: 2020-02-26 16:06
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(transactionManager = "db1DataSourceTransactionManager", rollbackFor = Exception.class)
    public void saveUser(User user) {
        userMapper.addUser(user.getName(), user.getPassword());
    }

}
