package com.free.scheduling.client.config;

import com.free.scheduling.client.annotation.JobCallBackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author funanbing
 * @date 2025-10-11 16:35:27
 * @description
 */
@Configuration
public class SchedulingConfig {

    @Bean
    public JobCallBackHandler jobCallBackHandler() {
        return new JobCallBackHandler();
    }
}
