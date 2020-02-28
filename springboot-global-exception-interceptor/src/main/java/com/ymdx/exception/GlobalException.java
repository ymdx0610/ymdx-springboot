package com.ymdx.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GlobalException
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-25 15:59
 * @Version: 1.0
 **/
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception exception){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("errorCode", 500);
        resultMap.put("errorMsg", exception.toString());
        return resultMap;
    }

}
