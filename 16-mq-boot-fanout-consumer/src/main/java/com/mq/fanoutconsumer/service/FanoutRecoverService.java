package com.mq.fanoutconsumer.service;

public interface FanoutRecoverService {
    void receive();
    void fanoutReceive01(String message);
    void fanoutReceive02(String message);
}
