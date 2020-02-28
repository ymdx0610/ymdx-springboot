package com.ymdx.service.impl;

import com.github.pagehelper.PageHelper;
import com.ymdx.dao.UserMapper;
import com.ymdx.pojo.User;
import com.ymdx.pojo.UserExample;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: impl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 14:21
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> findUsers(int pageNum, int pageSize) {
        UserExample example = new UserExample();
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

}
