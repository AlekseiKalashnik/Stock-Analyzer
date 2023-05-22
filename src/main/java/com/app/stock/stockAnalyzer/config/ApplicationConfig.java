package com.app.stock.stockAnalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {

    @Value("${exc.service.threads.amount}")
    private Integer amountOfThread;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(amountOfThread);
    }
}
