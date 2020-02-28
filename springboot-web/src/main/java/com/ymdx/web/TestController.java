package com.ymdx.web;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 14:47
 * @Version: 1.0
 **/
@RestController
public class TestController {

    // Spring Boot默认配置了消息转换器
    // 定义消息转换器
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter(){
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }

    @RequestMapping("/test1")
    public String test1(){
        return "hello，你好";
    }

}
