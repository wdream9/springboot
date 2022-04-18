package com.dubbo.service.impl;

import com.dubbo.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @DubboService  加上注解为我们干了两件事：
 *                          1、服务注册，把服务注册到zookeeper注册中心
 *                                      StudentService 的包名就是zk节点的名称
 *                                      StudentServiceImpl 是provider服务真正的提供者
 *                          2、服务暴露：
 */
@DubboService(timeout = 6000)
public class StudentServiceImpl implements StudentService {
    @Override
    public String getStudentName(int id) {
        return "provider--springboot---" + id;
    }
}
