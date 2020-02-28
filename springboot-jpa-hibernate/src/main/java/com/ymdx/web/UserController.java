package com.ymdx.web;

import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 07:45
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setName("小刚");
        user.setPassword("123456");
        user.setEmail("xiaogang@163.com");
        user.setBirthday(new Date());

        userService.addUser(user);

        return "success";
    }

    @RequestMapping("/findUser/{id}")
    public User findUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @RequestMapping("/delUser/{id}")
    public String delUser(@PathVariable Long id){
        userService.delUser(id);
        return "删除成功";
    }

}
