package com.core.testingmodule.domain.user;

import com.core.domain.models.user.User;
import com.core.domain.models.user.events.UserCreated;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testConstructorWithNewUserPublishesEvent() {
        User user = new User(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN"
        );

        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("testuser");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getFullName()).isEqualTo("Test User");
        assertThat(user.getAddress()).isEqualTo("123 Main St");
        assertThat(user.getRole()).isEqualTo("ADMIN");
        assertThat(user.getCreatedAt()).isNotNull();

        // verify domain event
        assertThat(user.getDomainEvents()).hasSize(1);
        assertThat(user.getDomainEvents().get(0)).isInstanceOf(UserCreated.class);
        UserCreated event = (UserCreated) user.getDomainEvents().get(0);
        assertThat(event.getId()).isEqualTo(user.getId());
        assertThat(event.getUsername()).isEqualTo("testuser");
    }

    @Test
    void testConstructorForRehydration() {
        UUID id = UUID.randomUUID();
        DateValue createdAt = DateValue.from(LocalDate.of(2024, 1, 1));

        User user = new User(
                id,
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN",
                createdAt
        );

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getCreatedAt()).isEqualTo(createdAt);

        // verify no domain events published on rehydration
        assertThat(user.getDomainEvents()).isEmpty();
    }
}
