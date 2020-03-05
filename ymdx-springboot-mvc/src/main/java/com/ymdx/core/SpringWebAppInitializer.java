package com.ymdx.core;

import com.ymdx.config.RootConfig;
import com.ymdx.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @ClassName: SpringWebAppInitializer
 * @Description: 加载SpringMVC DispatcherServlet
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-05 14:49
 * @Version: 1.0
 **/
public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
