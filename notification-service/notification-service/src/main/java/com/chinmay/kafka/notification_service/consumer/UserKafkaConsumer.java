package com.chinmay.kafka.notification_service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    // Crating 3 consumers for the same topic to demonstrate how messages are distributed across partitions
    // and consumed by different consumers in a consumer group.

    // Each consumer will consume messages from the same topic but from different partitions.
    @KafkaListener(topics = "user-random-topic")
    public void consume1(String message) {
        log.info("consume1: {}", message);
    }

    // Each consumer will consume messages from the same topic but from different partitions.
    @KafkaListener(topics = "user-random-topic")
    public void consume2(String message) {
        log.info("consume2: {}", message);
    }

    // Each consumer will consume messages from the same topic but from different partitions.
    @KafkaListener(topics = "user-random-topic")
    public void consume3(String message) {
        log.info("consume3: {}", message);
    }
}
