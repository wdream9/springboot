package com.dubbo.controller;

import com.dubbo.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo/consumer")
public class StudentController {
    // <dubbo:reference
    /**
     * @DubboReference  消费者去注册中心拿到该业务对象，注入到本地容器中
     */
    @DubboReference
    private StudentService studentService;

    @GetMapping("/getId")
    public String getStudentName(){
         return studentService.getStudentName(123456);
    }

}
