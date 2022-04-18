package com.boot.dubbo.controller;

import com.boot.dubbo.service.StudentService;
import com.boot.entity.Student;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class PersonController {
    @DubboReference
    private StudentService studentService;

    @GetMapping("/find")
    public String findStudent(){
        Student ss = studentService.findStudent("wangasdfasdddddddasdfasdfasdf");
        return ss.toString();
    }
}
