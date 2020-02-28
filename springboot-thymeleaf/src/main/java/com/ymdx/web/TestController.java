package com.ymdx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 13:28
 * @Version: 1.0
 **/
@Controller
public class TestController {

    @RequestMapping("/show")
    public String show(Model model){

        model.addAttribute("word", "义码当仙-thymeleaf");
        return "show";
    }

}
