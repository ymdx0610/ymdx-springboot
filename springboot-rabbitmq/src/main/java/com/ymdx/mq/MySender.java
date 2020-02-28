package com.ymdx.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MySender
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:49
 * @Version: 1.0
 **/
@Component
public class MySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("testQueue1", "你好！！！");
    }

}