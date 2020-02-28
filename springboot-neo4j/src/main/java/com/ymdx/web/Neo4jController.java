package com.ymdx.web;

import com.ymdx.pojo.UserNode;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: Neo4jController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:30
 * @Version: 1.0
 **/
@RestController
public class Neo4jController {

    @Autowired
    private UserService userService;

    @RequestMapping("/saveUser")
    public String saveUserNode(){
        UserNode userNode = new UserNode();
        userNode.setNodeId(1L);
        userNode.setUserId("11");
        userNode.setUserName("张三");
        userNode.setAge(25);
        userService.addUserNode(userNode);
        return "success";
    }

    @RequestMapping("/findUsers")
    public List<UserNode> findUsers(){
        return userService.findUsers();
    }


}