package com.ymdx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @ClassName: App
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:27
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "com.ymdx.dao")
@EntityScan(basePackages = "com.ymdx.pojo")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
