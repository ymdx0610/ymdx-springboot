package com.ymdx;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AdminUiServer
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-04 15:14
 * @Version: 1.0
 **/
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminUiServer {

    public static void main(String[] args) {
        SpringApplication.run(AdminUiServer.class, args);
    }

}
