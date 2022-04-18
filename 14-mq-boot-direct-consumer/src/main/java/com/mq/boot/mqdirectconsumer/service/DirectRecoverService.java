package com.mq.boot.mqdirectconsumer.service;

public interface DirectRecoverService {
    void receive();
    void directReceive(String message);
}
