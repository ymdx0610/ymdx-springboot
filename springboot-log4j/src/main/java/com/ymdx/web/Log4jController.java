package com.ymdx.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: Log4jController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 14:04
 * @Version: 1.0
 **/
@RestController
public class Log4jController {
    private static Logger logger = Logger.getLogger(Log4jController.class);

    @RequestMapping("/show")
    public String show(){

        logger.debug("debug日志");
        logger.info("info日志");
        logger.warn("warn日志");
        logger.error("error日志***");

        return "show";
    }

}
