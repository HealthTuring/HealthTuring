package com.healthturing.healthturing_server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "emailExecutor")
    public Executor emailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);  // Número mínimo de hilos
        executor.setMaxPoolSize(10);  // Número máximo
        executor.setQueueCapacity(100);  // Cola
        executor.setThreadNamePrefix("EmailThread-");
        executor.initialize();
        return executor;
    }
}
