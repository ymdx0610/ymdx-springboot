package com.ymdx.web;

import com.ymdx.activemq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:06
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @Autowired
    private Producer producer;

    @RequestMapping("/activemqTest")
    public String activemqTest() {
        // 点对点消息
        Destination dest = new ActiveMQQueue("myQueue");
        for (int i = 0; i < 3; i++) {
            producer.sendMessage(dest, "hello");
        }

        return "success";
    }

}
