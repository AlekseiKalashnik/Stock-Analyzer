package com.app.stock.stockAnalyzer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaMessage(String kafkaMessage) {
        kafkaTemplate.send(topic, kafkaMessage);
        log.info("produced {}", kafkaMessage);
    }
}
