package com.core.testingmodule.application.outbox;

import com.core.application.outbox.mapper.OutboxPayloadMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OutboxPayloadMapperTest {

    @Test
    void testSupportsAndToPayload() {
        // Create a mock implementation of the interface for testing
        OutboxPayloadMapper<String> mapper = mock(OutboxPayloadMapper.class);

        when(mapper.supports("event")).thenReturn(true);
        when(mapper.toPayload("event")).thenReturn(Map.of("key", "value"));

        assertTrue(mapper.supports("event"));
        Map<String, Object> payload = mapper.toPayload("event");
        assertNotNull(payload);
        assertEquals("value", payload.get("key"));

        verify(mapper).supports("event");
        verify(mapper).toPayload("event");
    }
}
