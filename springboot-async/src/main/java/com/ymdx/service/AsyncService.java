package com.ymdx.service;

import java.util.concurrent.Future;

/**
 * @ClassName: AsyncService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-26 10:17
 * @Version: 1.0
 **/
public interface AsyncService {
    Future<String> doTask1() throws Exception;
    Future<String> doTask2() throws Exception;
    Future<String> doTask3() throws Exception;
}
