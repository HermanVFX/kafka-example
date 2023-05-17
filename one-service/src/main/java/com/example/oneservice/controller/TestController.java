package com.example.oneservice.controller;

import com.example.oneservice.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/kafka")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public String sentMessage(@RequestBody UserDto user) {
        this.kafkaTemplate.send("transaction-1", user);
        return "Hello World!";
    }

    @KafkaListener(topics = "transaction-1")
    public void listener(@Payload UserDto user, ConsumerRecord<String, UserDto> cr) {
        log.info("Topic [transaction-1] Received message from {}", user.getName());
        log.info(cr.toString());
    }
}
