package com.genersoft.iot.vmp.conf.mqtt;

import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqttGatewayPublish {

    private static final int DEFAULT_QOS = 0;
    public static final int DEFAULT_RETRY_COUNT = 2;
    public static final int DEFAULT_RETRY_TIMEOUT = 3000;

    @Resource
    private IMqttMessageGateway messageGateway;

    @Resource
    private MqttProperties mqttProperties;

    public void publish(String topic, Object request) {
        publish(topic, DEFAULT_QOS, request);
    }

    public void publish(String topic, int qos, Object request) {
        byte[] payload = JsonUtils.toJsonByte(request);
        messageGateway.publish(topic, payload, qos);
    }


}
