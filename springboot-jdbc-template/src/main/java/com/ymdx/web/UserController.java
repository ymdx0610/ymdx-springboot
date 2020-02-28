package com.ymdx.web;

import com.ymdx.pojo.User;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 13:41
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/saveUser")
    @ResponseBody
    public String save(){
        User user = new User();
        user.setName("义码当仙");
        user.setPassword("666666");
        user.setEmail("ymdx@163.com");
        user.setBirthday(new Date());

        userService.save(user);
        return "success";
    }

}
