package com.core.application.outbox;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PreDestroy;

import java.util.List;

@Service
public class OutboxPublisherService {

	private final IOutboxRepository outboxRepository;
	private final ServiceBusSenderClient senderClient;

	public OutboxPublisherService(IOutboxRepository outboxRepository,
								  @Value("${azure.servicebus.connection-string}") String connectionString,
								  @Value("${azure.servicebus.topic-name}") String topicName) {

		this.outboxRepository = outboxRepository;
		this.senderClient = new ServiceBusClientBuilder()
			.connectionString(connectionString)
			.sender()
			.topicName(topicName)
			.buildClient();
	}

	@Scheduled(fixedDelay = 10000)
	public void processOutboxMessages() {
		List<OutboxMessage> messages = outboxRepository.findByProcessed(false);

		for (OutboxMessage message : messages) {
			try {
				senderClient.sendMessage(new ServiceBusMessage(message.getBody()));

				message.process();
				outboxRepository.save(message);
				System.out.println("Message sent successfully! - " + message.getBody());
			} catch (Exception ex) {
				System.out.println("Failed to send message: " + message.getId() + ": " + ex.getMessage());
			}
		}
	}

	@PreDestroy
	public void cleanup() {
		senderClient.close();
	}
}
