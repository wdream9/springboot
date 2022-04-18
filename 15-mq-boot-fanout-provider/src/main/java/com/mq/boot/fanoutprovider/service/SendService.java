package com.mq.boot.fanoutprovider.service;

public interface SendService {
    void FanoutSendMessage(String message);
}