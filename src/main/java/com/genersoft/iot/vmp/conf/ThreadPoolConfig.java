package com.genersoft.iot.vmp.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:42:19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolConfig {

    private int coreSize;

    private int maxSize;

    private long keepAliveTime;

    private int capacity;
}
