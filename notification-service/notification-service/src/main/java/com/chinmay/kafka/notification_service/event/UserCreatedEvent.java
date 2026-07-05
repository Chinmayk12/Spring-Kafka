package com.chinmay.kafka.notification_service.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String name;
    private String email;
}
