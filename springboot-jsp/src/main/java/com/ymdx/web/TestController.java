package com.ymdx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 10:42
 * @Version: 1.0
 **/
@Controller
public class TestController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "李四");
        return "index";
    }

}
