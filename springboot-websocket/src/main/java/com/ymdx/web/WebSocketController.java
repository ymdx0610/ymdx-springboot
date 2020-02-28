package com.ymdx.web;

import com.ymdx.pojo.SocketMessage;
import com.ymdx.pojo.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: WebSocketController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-28 07:30
 * @Version: 1.0
 **/
@Controller
public class WebSocketController {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 当浏览器向服务器端发送STOMP请求时，通过@MessageMapping注解来映射/getServerTime地址
    @MessageMapping(value = "/getServerTime")
    // 当服务器端有消息时，会对订阅了@SendTo中的路径的客户端发送消息
    @SendTo(value = "/topic/getResponse")
    public SocketResponse serverTime(SocketMessage message){
        return new SocketResponse(message.getMessage() + sf.format(new Date()));
    }

    @RequestMapping("/index")
    public String toPage(){
        return "webSocket";
    }

}