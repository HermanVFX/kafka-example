package com.example.twoservice.controller;

import com.example.twoservice.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/kafka")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ConsumerController {
    @KafkaListener(topics = "transaction-1")
    public void listener(@Payload UserDto user, ConsumerRecord<String, UserDto> cr) {
        log.info("Topic [transaction-1] Received message from {}", user.getName());
        log.info(cr.toString());
    }
}
