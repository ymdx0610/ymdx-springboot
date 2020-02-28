package com.ymdx.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Consumer
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:11
 * @Version: 1.0
 **/
@Component
public class Consumer {

    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message) {
        System.out.println(message + "......");
    }

}