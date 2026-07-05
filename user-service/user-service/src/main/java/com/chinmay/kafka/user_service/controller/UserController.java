package com.chinmay.kafka.user_service.controller;

import com.chinmay.kafka.user_service.dto.UserDto;
import com.chinmay.kafka.user_service.entity.User;
import com.chinmay.kafka.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    // Injecting the KafkaTemplate to send messages to Kafka
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    // Injecting the UserService to handle user creation
    private final UserService userService;

    //  Injecting the Kafka topic name from application.properties
    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        log.info("Received request to create user: {}", userDto);
        userService.createUser(userDto);
        return ResponseEntity.ok("User Is Created");
    }

    @PostMapping("{message}")
    public ResponseEntity<String> sendMessage(@PathVariable  String message) {
        log.info("Sending 1000 messages...");

        // Sending 1000 messages to the Kafka topic
        for(int i=0; i<1000; i++) {
            // Using i%3 as the key so messages are perfectly divided across the 3 partitions!
            String key = String.valueOf(i % 3);
            // Sending the message to the Kafka topic with the key and message
            kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, key, message + i);
        }
        log.info("Messages queued");
        return ResponseEntity.ok("Message Queued");
    }
}
