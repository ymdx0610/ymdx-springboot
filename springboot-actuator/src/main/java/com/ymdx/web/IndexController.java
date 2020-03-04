package com.ymdx.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-04 14:57
 * @Version: 1.0
 **/
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "springboot-actuator";
    }

}
