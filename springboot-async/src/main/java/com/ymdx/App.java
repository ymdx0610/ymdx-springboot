package com.ymdx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: App
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 10:16
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableAsync // 开启异步调用
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
