package com.ymdx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: FileProperties
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 08:46
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
