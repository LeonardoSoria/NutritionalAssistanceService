package com.core.testingmodule.domain.outbox;

import com.core.domain.models.outbox.OutboxMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class OutboxMessageTest {

    @Test
    void testConstructorWithDefaults() {
        LocalDateTime now = LocalDateTime.now();
        OutboxMessage message = new OutboxMessage("TestEvent", "{ \"data\": \"value\" }", now);

        assertThat(message.getId()).isNotNull();
        assertThat(message.getEventType()).isEqualTo("TestEvent");
        assertThat(message.getBody()).isEqualTo("{ \"data\": \"value\" }");
        assertThat(message.getTimestamp()).isEqualTo(now);
        assertThat(message.getSource()).isEqualTo("nutritional-service");
        assertThat(message.getEventVersion()).isEqualTo("1.0");
        assertThat(message.isProcessed()).isFalse();
    }

    @Test
    void testFullConstructor() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        OutboxMessage message = new OutboxMessage(
            id,
            "TestEvent",
            "2.0",
            "{ \"data\": \"value\" }",
            timestamp,
            "custom-source",
            true
        );

        assertThat(message.getId()).isEqualTo(id);
        assertThat(message.getEventType()).isEqualTo("TestEvent");
        assertThat(message.getEventVersion()).isEqualTo("2.0");
        assertThat(message.getBody()).isEqualTo("{ \"data\": \"value\" }");
        assertThat(message.getTimestamp()).isEqualTo(timestamp);
        assertThat(message.getSource()).isEqualTo("custom-source");
        assertThat(message.isProcessed()).isTrue();
    }

    @Test
    void testProcessSuccessfully() {
        OutboxMessage message = new OutboxMessage("TestEvent", "{}", LocalDateTime.now());
        assertThat(message.isProcessed()).isFalse();

        message.process();

        assertThat(message.isProcessed()).isTrue();
    }

    @Test
    void testProcessThrowsWhenAlreadyProcessed() {
        OutboxMessage message = new OutboxMessage("TestEvent", "{}", LocalDateTime.now());
        message.process(); // mark processed

        assertThatThrownBy(message::process)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("This outbox message has already been processed");
    }
}
