package com.core.testingmodule.domain.user;

import com.core.domain.models.user.events.UserCreated;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserCreatedTest {

    @Test
    void testUserCreatedEvent() {
        UUID id = UUID.randomUUID();
        DateValue createdAt = DateValue.from(LocalDate.of(2024, 7, 4));

        UserCreated event = new UserCreated(
            id,
            "testuser",
            "password123",
            "test@example.com",
            "Test User",
            "123 Main St",
            createdAt
        );

        assertThat(event.getId()).isEqualTo(id);
        assertThat(event.getUsername()).isEqualTo("testuser");
        assertThat(event.getPassword()).isEqualTo("password123");
        assertThat(event.getEmail()).isEqualTo("test@example.com");
        assertThat(event.getFullName()).isEqualTo("Test User");
        assertThat(event.getAddress()).isEqualTo("123 Main St");
        assertThat(event.getCreatedAt()).isEqualTo(createdAt.toLocalDate());
        assertThat(event.getEventType()).isEqualTo("USER_CREATED");
    }
}
