package com.genersoft.iot.vmp.conf.mqtt;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executor;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:39:20
 */
@Configuration
public class MqttMessageChannel {

    @Resource
    @Qualifier("mqttThreadPool")
    private Executor threadPool;

    @Bean(name = ChannelName.INBOUND)
    public MessageChannel inboundChannel() {
        return new ExecutorChannel(threadPool);
    }

    @Bean(name = ChannelName.DEFAULT)
    public MessageChannel defaultChannel() {
        return new DirectChannel();
    }

    @Bean(name = ChannelName.INBOUND_OSD)
    public MessageChannel inboundOsdChannel() {
        return new ExecutorChannel(threadPool);
    }

    @Bean(name = ChannelName.INBOUND_INNER_EVENTS)
    public MessageChannel inboundInnerEventsChannel() {
        return new ExecutorChannel(threadPool);
    }

}
