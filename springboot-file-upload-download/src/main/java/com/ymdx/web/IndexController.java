package com.ymdx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:06
 * @Version: 1.0
 **/
@Controller
public class IndexController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

}
