package com.boot.dubbo.controller;

import com.boot.entity.Student;
import com.boot.dubbo.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @DubboReference(version = "1.0")
    private StudentService studentService;
    @GetMapping("/query")
    public Student queryStudent(int id, String name){
        Student student = studentService.querySstudent(id, name);
        return student;
    }
}
