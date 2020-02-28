package com.ymdx.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 15:09
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @RequestMapping("/err")
    public String err(){
        int a = 1/0;
        return "success";
    }

}
