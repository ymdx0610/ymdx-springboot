package com.ymdx.web;

import com.ymdx.pojo.User;
import com.ymdx.pojo.UserInfo;
import com.ymdx.service.UserInfoService;
import com.ymdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 16:08
 * @Version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setName("小丽");
        user.setPassword("000000");
        userService.saveUser(user);
        return "success";
    }

    @RequestMapping("/addUserInfo")
    public String addUserInfo(){

        UserInfo userInfo = new UserInfo();
        userInfo.setName("小丽");
        userInfo.setPhone("18910621111");
        userInfo.setAddress("北京朝阳");
        userInfoService.saveUserInfo(userInfo);

        return "success";
    }

}
