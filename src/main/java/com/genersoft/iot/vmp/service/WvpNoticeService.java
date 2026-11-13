package com.genersoft.iot.vmp.service;

public interface WvpNoticeService {

    /**
     * 推送流开启关闭通知
     * @param app
     * @param streamName
     * @param action
     */
    void sendZlmStreamHook(String app,String streamName,Integer action);
}
