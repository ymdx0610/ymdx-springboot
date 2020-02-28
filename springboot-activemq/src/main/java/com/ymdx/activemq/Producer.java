package com.ymdx.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @ClassName: Producer
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:09
 * @Version: 1.0
 **/
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * @param dest
     * @param message
     */
    public void sendMessage(Destination dest, String message){
        jmsMessagingTemplate.convertAndSend(dest, message);
    }

}