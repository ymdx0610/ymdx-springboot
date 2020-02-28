package com.ymdx.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SpringController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 13:28
 * @Version: 1.0
 **/
// 如果每一个方法都返回restful内容，则可以用使用RestController注解
@RestController
public class MvcController {

    @RequestMapping("/show")
    // 返回Restful内容，不使用@RestController注解，而使用@Controller注解则为跳转
//    @ResponseBody
    public String show() {
        return "show";
    }

    // 支持Restful风格
    @RequestMapping("/info/{msg}")
    public String info(@PathVariable String msg) {
        return "show " + msg;
    }

}
