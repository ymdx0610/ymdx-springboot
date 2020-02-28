package com.ymdx.web;

import com.ymdx.pojo.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/show")
    public Object show(){
        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        person.setBirthday(new Date());
        return person;
    }

    @RequestMapping("/err")
    public String err(){
        int a = 11/0;
        return "success";
    }

}
