package com.ymdx.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BookController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 11:50
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix = "book")// 基于类型安全的配置，各个自定义属性具有相同的前缀，且需要提供set/get方法
@RestController
public class BookController {

//    @Value("${book.author}")
    private String author;

//    @Value("${book.name}")
    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RequestMapping("/bookInfo")
    public String showInfo(){
        return author+" : "+name;
    }

}
