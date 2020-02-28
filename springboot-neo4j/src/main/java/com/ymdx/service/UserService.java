package com.ymdx.service;

import com.ymdx.dao.UserRepository;
import com.ymdx.pojo.UserNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:30
 * @Version: 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUserNode(UserNode userNode){
        userRepository.addUserNodeList(userNode.getUserName(), userNode.getAge());
    }

    public List<UserNode> findUsers() {
        return userRepository.getUserNodeList();
    }

}