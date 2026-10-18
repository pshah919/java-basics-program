package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "paymentTaskExecutor")
    public ThreadPoolTaskExecutor paymentTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Core configuration
        executor.setCorePoolSize(10);             // Active threads always kept alive
        executor.setMaxPoolSize(50);              // Max threads if queue fills up
        executor.setQueueCapacity(200);           // Work queue capacity
        executor.setThreadNamePrefix("PaymentPool-"); // Thread naming prefix

        // Rejection Policy when max pool size & queue capacity are both full
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // Graceful shutdown configuration
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);

        executor.initialize();
        return executor;
    }
}
