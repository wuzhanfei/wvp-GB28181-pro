package com.genersoft.iot.vmp.conf.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:29:33
 */
@Configuration
@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttProperties {

    private MqttChannelEnum channelEnum = MqttChannelEnum.EMQX;

    private String host;

    private String username;

    private String password;

    private String clientId;

    private List<String> topics;
}
