package com.chinmay.kafka.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    // Creating a kafka topic using configs
    // This is required because we are using a dockerized kafka cluster and the topic will not be created automatically
    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    @Bean
    public NewTopic userTopic() {
        // Creating a topic with 3 partitions and 1 replication factor
        return new NewTopic(KAFKA_RANDOM_USER_TOPIC, 3, (short) 1);
    }

    @Bean
    public NewTopic userCreatedTopic() {
        // Creating a topic with 3 partitions and 1 replication factor
        return new NewTopic(KAFKA_USER_CREATED_TOPIC, 3, (short) 1);
    }
}
