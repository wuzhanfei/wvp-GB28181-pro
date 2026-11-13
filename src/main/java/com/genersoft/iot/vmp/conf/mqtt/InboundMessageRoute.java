package com.genersoft.iot.vmp.conf.mqtt;

import com.genersoft.iot.vmp.utils.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import java.util.Collection;
import java.util.Collections;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:54:11
 */
@Configuration
@Slf4j
public class InboundMessageRoute extends AbstractMessageRouter {
    @Router(inputChannel = ChannelName.INBOUND)
    protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
        byte[] payload = (byte[])message.getPayload();
//        log.debug("received topic: {} \t payload =>{}", topic, new String(payload));
        TopicEnum topicEnum = TopicEnum.find(topic);
        MessageChannel bean = (MessageChannel) SpringBeanFactory.getBean(topicEnum.getBeanName());

        return Collections.singleton(bean);
    }
}
