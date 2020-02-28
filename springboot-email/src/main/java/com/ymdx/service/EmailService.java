package com.ymdx.service;

import java.io.File;

/**
 * @ClassName: EmailService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 08:04
 * @Version: 1.0
 **/
public interface EmailService {

    /**
     * 发送简单的邮件
     * @param sendTo
     * @param title
     * @param content
     */
    void sendSimpleMail(String sendTo, String title, String content);

    /**
     * 发送带附件的邮件
     * @param sendTo
     * @param title
     * @param content
     * @param file
     */
    void sendAttachmentMail(String sendTo, String title, String content, File file);

    /**
     * 发送模版邮件
     * @param sendTo
     * @param title
     * @param templateName
     */
    void sendTemplateMail(String sendTo, String title, String templateName);
}
