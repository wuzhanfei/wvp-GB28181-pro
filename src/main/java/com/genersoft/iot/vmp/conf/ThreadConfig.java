package com.genersoft.iot.vmp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadConfig {

    @Bean(name = "mqttThreadPool")
    public Executor threadPool(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(threadPoolConfig.getCoreSize(),
                threadPoolConfig.getMaxSize(), threadPoolConfig.getKeepAliveTime(),
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(threadPoolConfig.getCapacity()),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
