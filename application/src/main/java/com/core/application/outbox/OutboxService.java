package com.core.application.outbox;

import com.core.application.outbox.mapper.OutboxPayloadMapper;
import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OutboxService {

    private final IOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
	private final List<OutboxPayloadMapper<?>> mappers;

    public OutboxService(IOutboxRepository outboxRepository, ObjectMapper objectMapper,
						 List<OutboxPayloadMapper<?>> mappers) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
		this.mappers = mappers;
    }

	public void recordEvent(String eventType, Object payload) {
		try {
			Map<String, Object> bodyMap = mappers.stream()
				.filter(mapper -> mapper.supports(payload))
				.map(mapper -> ((OutboxPayloadMapper<Object>) mapper).toPayload(payload))
				.findFirst()
				.orElseGet(() -> objectMapper.convertValue(payload, Map.class));

			String body = objectMapper.writeValueAsString(bodyMap);
			OutboxMessage message = new OutboxMessage(eventType, body, LocalDateTime.now());
			outboxRepository.save(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to serialize event payload", e);
		}
	}
}
