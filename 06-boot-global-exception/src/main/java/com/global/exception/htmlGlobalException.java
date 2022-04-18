package com.global.exception;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 错误返回的是个页面
 */
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class htmlGlobalException {


    // 返回页面
    @ExceptionHandler(value = IOException.class)
    public ModelAndView errorHandlerByHtml(HttpServletRequest request, Exception e){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",e.getMessage());
        modelAndView.addObject("url",request.getRequestURL());

        modelAndView.setViewName("500");

        return modelAndView;
    }
}
