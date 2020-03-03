package com.ymdx.web;

import com.ymdx.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-03 13:50
 * @Version: 1.0
 **/
@Slf4j
@RestController
public class TestController {

    @RequestMapping("/findUser")
    public User findUser(){
        User user = new User();
        user.setName("ymdx");
        user.setAge(30);
        log.info("====用户信息====> {}", user);
        return user;
    }

}
