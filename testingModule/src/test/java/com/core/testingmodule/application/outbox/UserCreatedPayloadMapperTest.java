package com.core.testingmodule.application.outbox;

import com.core.application.outbox.mapper.UserCreatedPayloadMapper;
import com.core.domain.models.user.events.UserCreated;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserCreatedPayloadMapperTest {

    private UserCreatedPayloadMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserCreatedPayloadMapper();
    }

    @Test
    void supports_returnsTrueForUserCreated() {
        UserCreated event = new UserCreated(
            UUID.randomUUID(),
            "username",
            "password",
            "email@example.com",
            "Full Name",
            "Address",
            DateValue.from(LocalDate.now())
        );
        assertTrue(mapper.supports(event));
    }

    @Test
    void supports_returnsFalseForOtherTypes() {
        String notAnEvent = "Not an event";
        assertFalse(mapper.supports(notAnEvent));
        assertFalse(mapper.supports(null));
    }

    @Test
    void toPayload_mapsAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        String username = "user123";
        String email = "user@example.com";
        String fullName = "User FullName";
        DateValue createdAt = DateValue.from(LocalDate.of(2025, 7, 4));

        UserCreated event = new UserCreated(id, username, "password", email, fullName, "address", createdAt);

        Map<String, Object> payload = mapper.toPayload(event);

        assertEquals(id, payload.get("id"));
        assertEquals(username, payload.get("username"));
        assertEquals(email, payload.get("email"));
        assertEquals(fullName, payload.get("fullName"));
        assertEquals(createdAt.toLocalDate(), payload.get("createdAt"));
    }
}
