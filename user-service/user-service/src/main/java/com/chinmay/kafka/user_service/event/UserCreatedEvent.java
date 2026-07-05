package com.chinmay.kafka.user_service.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String name;
    private String email;
}
