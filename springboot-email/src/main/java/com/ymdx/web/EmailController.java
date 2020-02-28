package com.ymdx.web;

import com.ymdx.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @ClassName: EmailController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 08:03
 * @Version: 1.0
 **/
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(){
        emailService.sendSimpleMail("zhongyi.li@rograndec.com", "测试SpringBoot简单邮件发送", "你好，明天约吗？");
        return "success";
    }

    @RequestMapping("/sendAttachmentEmail")
    public String sendAttachmentEmail(){
        File file = new File("springboot-email/src/main/resources/static/attachment.jpg");
        emailService.sendAttachmentMail("zhongyi.li@rograndec.com",
                "测试SpringBoot带附件的邮件发送",
                "测试SpringBoot带附件的邮件发送", file);
        return "success";
    }

    @RequestMapping("/sendTemplateEmail")
    public String sendTemplateEmail(){
        emailService.sendTemplateMail("zhongyi.li@rograndec.com", "测试SpringBoot简单邮件发送", "info.html");
        return "success";
    }

}
