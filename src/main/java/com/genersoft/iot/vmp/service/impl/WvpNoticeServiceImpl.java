package com.genersoft.iot.vmp.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.common.mqtt.CommonTopicRequest;
import cn.iocoder.yudao.framework.common.mqtt.dto.TaskLivingNoticeDto;
import com.genersoft.iot.vmp.conf.mqtt.MqttGatewayPublish;
import com.genersoft.iot.vmp.conf.mqtt.TopicEnum;
import com.genersoft.iot.vmp.service.WvpNoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WvpNoticeServiceImpl implements WvpNoticeService {
    @Resource
    private MqttGatewayPublish mqttGatewayPublish;

    @Override
    public void sendZlmStreamHook(String app, String streamName, Integer action) {
        CommonTopicRequest<TaskLivingNoticeDto> request = new CommonTopicRequest<>();
        request.setTid(IdUtil.fastUUID());
        request.setBid(IdUtil.fastUUID());
        request.setTimestamp(System.currentTimeMillis());
        request.setMethod("task_live_notice");
        TaskLivingNoticeDto dto = new TaskLivingNoticeDto();
        dto.setAction(action);
        dto.setApp(app);
        dto.setStreamName(streamName);
        request.setData(dto);
        mqttGatewayPublish.publish(TopicEnum.INNER_EVENTS.getNotifyTopic(),2,request);
    }
}
