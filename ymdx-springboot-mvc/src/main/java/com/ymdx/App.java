package com.ymdx;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @ClassName: App
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-03-05 14:45
 * @Version: 1.0
 **/
public class App {

    public static void main(String[] args) throws ServletException, LifecycleException {
        start();
    }

    public static void start() throws ServletException, LifecycleException {
        // 创建Tomcat容器
        Tomcat tomcatServer = new Tomcat();
        // 设置端口号
        tomcatServer.setPort(9090);
        // 读取项目路径
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("ymdx-springboot-mvc/src/main").getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // 读取class文件地址
        File additionWebInfClasses = new File("target/classes");
        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取class文件
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        // 启动tomcat容器
        tomcatServer.start();
        // 异步等待请求执行
        tomcatServer.getServer().await();
    }

}
