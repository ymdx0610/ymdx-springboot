package com.ymdx.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyReceiver
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:49
 * @Version: 1.0
 **/
@Component
public class MyReceiver {

    @RabbitHandler
    @RabbitListener(queues = "testQueue1")
    public void receive(String msg){
        System.out.println("收到消息为："+msg+"...");
    }

}
