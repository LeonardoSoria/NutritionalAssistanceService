package com.core.testingmodule.application.outbox;

import com.core.application.outbox.dto.OutboxPayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutboxPayloadTest {

    @Test
    void testOutboxPayloadProperties() {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> body = Map.of("key", "value");

        OutboxPayload payload = new OutboxPayload(
                "USER_CREATED",
                "1.0",
                now,
                body,
                "nutritional-service"
        );

        assertThat(payload.getEventType()).isEqualTo("USER_CREATED");
        assertThat(payload.getEventVersion()).isEqualTo("1.0");
        assertThat(payload.getTimestamp()).isEqualTo(now);
        assertThat(payload.getBody()).containsEntry("key", "value");
        assertThat(payload.getSource()).isEqualTo("nutritional-service");
    }
}
