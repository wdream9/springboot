package com.global.exception;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 捕获带有  @controller、@restcontroller 注解的类报出错误信息
 */
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class JsonGlobalException {

    /**
     * @ResponseBody：统一返回json格式数据
     */
    // 处理运行时异常
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody Object errorHandlerByJson(HttpServletRequest request, Exception e){
        // 输出错误信息到控制台
        e.printStackTrace();
        return "服务器开小差了";
    }

    /**
     * io异常
     */
    @ExceptionHandler(IOException.class)
    public Object ioExceprion(HttpServletRequest request,Exception e){
        return "io流异常";
    }
}
