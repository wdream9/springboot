package com.boot.duboo.service.impl;

import com.boot.entity.Student;
import com.boot.dubbo.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  暴露dubbo服务
 */
@Component
@DubboService(interfaceClass = StudentServiceImpl.class, version = "1.0", timeout = 500)
public class StudentServiceImpl implements StudentService {
    @Override
    public Student querySstudent(int id, String name) {
        Student student = new Student(id,name,200);
        return student;
    }

    @Override
    public Student findStudent(String name) {

        return new Student(1,name,123);
    }
}
