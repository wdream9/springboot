package com.dubbo.controller;

import com.dubbo.service.SiteService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SiteController {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        context.start();
        SiteService siteService = (SiteService) context.getBean("siteService");
        System.out.println(siteService.getName("这是测试的"));
    }

}
