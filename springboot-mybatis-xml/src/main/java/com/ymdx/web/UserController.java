package com.ymdx.web;

import com.github.pagehelper.PageInfo;
import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: 2020-02-26 14:17
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/saveUser")
    public String saveUser(){
        User user = new User();
        user.setName("义码当仙2");
        user.setPassword("888888");
        user.setEmail("ymdx2@163.com");
        user.setBirthday(new Date());
        userService.addUser(user);

        return "success";
    }

    @RequestMapping("/findUsers/{pageNum}/{pageSize}")
    public PageInfo<User> findUsers(@PathVariable int pageNum, @PathVariable int pageSize){
        List<User> users = userService.findUsers(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

}
