package com.core.application.outbox;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.messaging.eventhubs.models.CreateBatchOptions;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.core.application.outbox.dto.OutboxPayload;
import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PreDestroy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OutboxPublisherService {

	private final IOutboxRepository outboxRepository;
	private final EventHubProducerClient producerClient;
	private final ObjectMapper objectMapper = new ObjectMapper()
		.registerModule(new JavaTimeModule())
		.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	public OutboxPublisherService(IOutboxRepository outboxRepository) {
		this.outboxRepository = outboxRepository;

		String connectionString = System.getenv("AZURE_EVENT_HUB_CONNECTION_STRING");
		String hubName = System.getenv("AZURE_EVENT_HUB_NAME");

		if (connectionString == null || hubName == null) {
			throw new IllegalStateException("Missing Azure Event Hub environment variables.");
		}

		this.producerClient = new EventHubClientBuilder()
			.connectionString(connectionString, hubName)
			.buildProducerClient();
	}

	@Scheduled(fixedDelay = 20000)
	public void processOutboxMessages() {
		List<OutboxMessage> messages = outboxRepository.findByProcessed(false);

		for (OutboxMessage message : messages) {
			try {
				Map<String, Object> bodyMap = objectMapper.readValue(
					message.getBody(), new TypeReference<Map<String, Object>>() {}
				);

				OutboxPayload payload = new OutboxPayload(
					message.getEventType(),
					message.getEventVersion(),
					message.getTimestamp(),
					bodyMap,
					message.getSource()
				);

				String payloadJson = objectMapper.writeValueAsString(payload);
				EventData eventData = new EventData(payloadJson);

				// Send as single-event batch
				EventDataBatch batch = producerClient.createBatch(new CreateBatchOptions());
				if (!batch.tryAdd(eventData)) {
					throw new IllegalArgumentException("Event too large for batch");
				}
				producerClient.send(batch);

				message.process();
				outboxRepository.save(message);
				System.out.println("Event sent successfully! - " + payloadJson);
			} catch (Exception ex) {
				System.out.println("Failed to send event: " + message.getId() + ": " + ex.getMessage());
			}
		}
	}

	@PreDestroy
	public void cleanup() {
		producerClient.close();
	}
}
