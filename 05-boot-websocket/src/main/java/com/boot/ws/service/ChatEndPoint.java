package com.boot.ws.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@Slf4j
@Component
@ServerEndpoint("/ws/{name}")
public class ChatEndPoint {

    @OnOpen
    public void openSession(@PathParam("name") String name, Session session){
        // 打开会话，前后端进行连接时需要执行的业务逻辑

    }

    @OnMessage
    public void OnMessage(@PathParam("name") String name, String message){
        // 前后端信息交互时需要执行的业务逻辑
    }

    @OnError
    public void OnError(@PathParam("name") String name, Session session){
        // 连接报错的时候就执行的逻辑
    }

    @OnClose
    public void OnClose(@PathParam("name") String name, Session session){
        // 关闭连接时需要做的业务逻辑
    }
}
