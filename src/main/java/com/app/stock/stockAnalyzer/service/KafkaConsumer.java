package com.app.stock.stockAnalyzer.service;

import com.app.stock.stockAnalyzer.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final EmailService emailService;

    //KafkaConsumerConfig добавить фабрику консюмеров
    @KafkaListener(topics = "topic-1")
    public void consumeKafkaMessage(String message) {
        emailService.sendSimpleEmail("SomeLogin4Y@yandex.ru",
                "Welcome to Stock-Analyzer", message);

        log.info("kafka message consumed {}", message);
    }
}
