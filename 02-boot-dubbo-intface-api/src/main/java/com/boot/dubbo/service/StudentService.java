package com.boot.dubbo.service;

import com.boot.entity.Student;

public interface StudentService {
    Student querySstudent(int id,String name);
    Student findStudent(String name);
}
