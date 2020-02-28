package com.ymdx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 11:31
 * @Version: 1.0
 **/
// exclude = {RedisAutoConfiguration.class} 关闭Redis自动配置
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class})// 启用自动配置
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    public static void main(String[] args){
        SpringApplication.run(HelloController.class, args);
    }

}
