package com.app.stock.stockAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void sendOrder(String msgId, String msg){
        kafkaTemplate.send("message", msgId, msg);
    }
}
