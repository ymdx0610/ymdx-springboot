package com.ymdx.web;

import com.ymdx.mq.MySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:48
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @Autowired
    private MySender mySender;

    @RequestMapping("/rabbitMqSendMsg")
    public String rabbitMqSendMsg(){
        mySender.send();
        return "ok";
    }

}