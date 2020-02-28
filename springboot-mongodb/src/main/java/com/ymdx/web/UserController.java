package com.ymdx.web;

import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 13:41
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/findUser/{name}")
    public User findUser(@PathVariable String name){
        return userService.findUserByName(name);
    }

    @RequestMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setName("小明");
        user.setPassword("111111");
        user.setEmail("xiaoming@163.com");
        user.setBirthday(new Date());
        userService.saveUser(user);
        return "success";
    }

    // -----MongoDB Demo-----

    @RequestMapping("/addUserToMongo")
    public String addUserToMongo(){
        User user = new User();
        user.setId(1L);
        user.setName("小明");
        user.setPassword("111111");
        user.setEmail("xiaoming@163.com");
        user.setBirthday(new Date());
        mongoTemplate.save(user);
        return "success";
    }

    @RequestMapping("/findAllUserFromMongo")
    public List<User> findAllUserFromMongo(){
        return mongoTemplate.findAll(User.class);
    }

}