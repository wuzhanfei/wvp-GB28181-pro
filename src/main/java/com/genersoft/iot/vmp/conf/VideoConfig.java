package com.genersoft.iot.vmp.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "video")
@Configuration
public class VideoConfig {

    private String taskStreamApp = "check_task_proxy";

    private boolean taskStreamEnableMp4 = false;
}
