package com.dubbo.service.impl;

import com.dubbo.service.SiteService;

public class SiteServiceImpl implements SiteService {
    @Override
    public String getName(String name) {
        return "服务提供者已经接收到消息";
    }
}
