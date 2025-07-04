package com.core.testingmodule.application.outbox;

import com.core.application.outbox.OutboxService;
import com.core.application.outbox.mapper.OutboxPayloadMapper;
import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

class OutboxServiceTest {

    private IOutboxRepository outboxRepository;
    private ObjectMapper objectMapper;
    private OutboxPayloadMapper<Object> payloadMapper;
    private OutboxService outboxService;

    @BeforeEach
    void setUp() {
        outboxRepository = mock(IOutboxRepository.class);
        objectMapper = new ObjectMapper();
        payloadMapper = mock(OutboxPayloadMapper.class);
        outboxService = new OutboxService(outboxRepository, objectMapper, List.of(payloadMapper));
    }

    @Test
    void recordEvent_withSupportedMapper_savesMappedMessage() {
        // arrange
        TestEvent payload = new TestEvent("test123");
        Map<String, Object> mappedPayload = Map.of("customKey", "customValue");

        when(payloadMapper.supports(payload)).thenReturn(true);
        when(payloadMapper.toPayload(payload)).thenReturn(mappedPayload);

        // act
        outboxService.recordEvent("TEST_EVENT", payload);

        // assert
        var captor = forClass(OutboxMessage.class);
        verify(outboxRepository).save(captor.capture());

        OutboxMessage savedMessage = captor.getValue();
        assert savedMessage.getEventType().equals("TEST_EVENT");
        assert savedMessage.getBody().contains("customKey");
    }

    @Test
    void recordEvent_withNoSupportingMapper_usesDefaultSerialization() {
        // arrange
        TestEvent payload = new TestEvent("test123");
        when(payloadMapper.supports(payload)).thenReturn(false);

        // act
        outboxService.recordEvent("TEST_EVENT", payload);

        // assert
        var captor = forClass(OutboxMessage.class);
        verify(outboxRepository).save(captor.capture());

        OutboxMessage savedMessage = captor.getValue();
        assert savedMessage.getEventType().equals("TEST_EVENT");
        assert savedMessage.getBody().contains("test123");
    }

    static class TestEvent {
        private final String value;

        TestEvent(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
