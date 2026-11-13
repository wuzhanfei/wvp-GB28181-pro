package com.genersoft.iot.vmp.conf.mqtt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.genersoft.iot.vmp.utils.Consts.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public enum TopicEnum {
    INNER_EVENTS(Pattern.compile("^"+ TOPIC_VMS_PRE + TOPIC_CLOUD + TOPIC_INNER_EVENTS+"$"), ChannelName.INBOUND_INNER_EVENTS,TOPIC_VMS_PRE + TOPIC_CLOUD+ TOPIC_INNER_EVENTS),

    UNKNOWN(Pattern.compile("^.*$"),ChannelName.DEFAULT,null);

    private final Pattern pattern;
    private final String beanName;
    private String notifyTopic;

    public static TopicEnum find(String topic) {
        return Arrays.stream(TopicEnum.values()).filter(topicEnum -> Objects.nonNull(topicEnum.pattern) && topicEnum.pattern.matcher(topic).matches()).findAny().orElse(UNKNOWN);
    }

    public static String getNotifyTopic(String sn,TopicEnum topicEnum){
        return String.format(topicEnum.getNotifyTopic(),sn);
    }
}
