package com.core.application.outbox;

import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OutboxService {

    private final IOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public OutboxService(IOutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    public void recordEvent(String eventType, Object payload) {
        try {
            String body = objectMapper.writeValueAsString(payload);
            OutboxMessage message = new OutboxMessage(eventType, body, LocalDateTime.now());
            outboxRepository.save(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event payload", e);
        }
    }
}
