package com.ymdx.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * @ClassName: KafkaProducer
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 14:55
 * @Version: 1.0
 **/
@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        String msg = UUID.randomUUID().toString();
        // 发送消息
        ListenableFuture future = kafkaTemplate.send("test", msg);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + msg), throwable -> System.out.println("消息发送失败：" + msg));
    }

}