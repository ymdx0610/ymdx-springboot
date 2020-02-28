package com.ymdx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 13:21
 * @Version: 1.0
 **/
@Controller
public class TestController {

    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("name", "义码当仙");
        return "show";
    }

    @RequestMapping("/ftl")
    public String freemarkerIndex(Map<String, Object> result) {
        result.put("name", "ymdx");
        result.put("sex", "0");

        List<String> users = new ArrayList<>();
        users.add("张三");
        users.add("李四");
        users.add("义码当仙");
        result.put("users", users);
        return "test";
    }

}
