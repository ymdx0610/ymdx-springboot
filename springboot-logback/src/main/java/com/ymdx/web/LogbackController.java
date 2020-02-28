package com.ymdx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LogbackController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 13:40
 * @Version: 1.0
 **/
@RestController
public class LogbackController {
    private static Logger logger = LoggerFactory.getLogger(LogbackController.class);

    @RequestMapping("/show")
    public String show(){

        logger.debug("debug日志");
        logger.info("info日志");
        logger.warn("warn日志");
        logger.error("error日志");

        return "show";
    }

}
