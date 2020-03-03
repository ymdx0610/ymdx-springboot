package com.ymdx.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-03 11:56
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @RequestMapping("/show")
    public String show(String name, String age){
        String info = "name = " + name + "; age = " + age;
        return info;
    }

}
