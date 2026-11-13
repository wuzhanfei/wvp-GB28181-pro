package com.genersoft.iot.vmp.conf.mqtt;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import java.util.UUID;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:47:56
 */
@IntegrationComponentScan
@Configuration
@Slf4j
public class MqttInBoundConfiguration {

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private MqttPahoClientFactory mqttClientFactory;

    @Resource(name = ChannelName.INBOUND)
    private MessageChannel inboundChannel;

    /**
     * Clients of inbound message channels.
     * @return
     */
//    @Bean
//    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
//                UUID.randomUUID().toString(), mqttClientFactory, mqttProperties.getTopics().toArray(new String[0]));
//        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
//        // use byte types uniformly
//        converter.setPayloadAsBytes(true);
//        adapter.setConverter(converter);
//        adapter.setQos(1);
//        adapter.setOutputChannel(inboundChannel);
//        return adapter;
//    }

    /**
     * Clients of outbound message channels.
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = ChannelName.OUTBOUND)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                UUID.randomUUID().toString(), mqttClientFactory);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        // use byte types uniformly
        converter.setPayloadAsBytes(true);

        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(0);
        messageHandler.setConverter(converter);
        return messageHandler;
    }



    /**
     * Define a default channel to handle messages that have no effect.
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = ChannelName.DEFAULT)
    public MessageHandler defaultInboundHandler() {
        return message -> {
            log.info("The default channel does not handle messages." +
                    "\nTopic: " + message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC) +
                    "\nPayload: " + message.getPayload() + "\n");
        };
    }
}
