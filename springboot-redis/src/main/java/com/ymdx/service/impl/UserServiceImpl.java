package com.ymdx.service.impl;

import com.ymdx.dao.UserMapper;
import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 11:37
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisCluster jedisCluster;

    @Cacheable(value = "userName")
    @Override
    public User findUserByName(String name) {
        System.out.println("从数据库中查询...");
        return userMapper.findUserByName(name);
    }

    @Override
    public void saveUser(User user) {
        userMapper.addUser(user.getName(), user.getPassword());
    }

    @Override
    public String findRedis() {
        jedisCluster.set("test1", "123456");
        return "JedisCluster:" + jedisCluster.get("test1");
    }

}
