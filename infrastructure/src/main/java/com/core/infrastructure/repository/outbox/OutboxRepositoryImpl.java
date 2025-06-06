package com.core.infrastructure.repository.outbox;

import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.outbox.OutboxMessage;
import com.core.infrastructure.entity.OutboxMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class OutboxRepositoryImpl implements IOutboxRepository {

    @Autowired
    private OutboxCrudRepository outboxCrudRepository;

	@Override
	public void save(OutboxMessage outboxMessage) {
		OutboxMessageEntity entity = new OutboxMessageEntity(
			outboxMessage.getId(),
			outboxMessage.getEventType(),
			outboxMessage.getEventVersion(),
			outboxMessage.getBody(),
			outboxMessage.getTimestamp(),
			outboxMessage.getSource(),
			outboxMessage.isProcessed()
		);
		outboxCrudRepository.save(entity);
	}

	@Override
	public List<OutboxMessage> findByProcessed(boolean processed) {
		List<OutboxMessageEntity> entities = outboxCrudRepository.findByProcessed(processed);
		return entities.stream()
			.map(entity -> new OutboxMessage(
				entity.getId(),
				entity.getEventType(),
				entity.getEventVersion(),
				entity.getBody(),
				entity.getTimestamp(),
				entity.getSource(),
				entity.isProcessed()
			))
			.toList();
	}
}
