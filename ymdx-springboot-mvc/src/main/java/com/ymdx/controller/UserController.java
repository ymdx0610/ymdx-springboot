package com.ymdx.controller;

import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-05 14:53
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showInfo", produces = "text/html;charset=UTF-8")
    public String showInfo() {
        return userService.showInfo();
    }

}
