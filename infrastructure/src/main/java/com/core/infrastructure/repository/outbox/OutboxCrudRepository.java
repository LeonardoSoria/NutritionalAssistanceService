package com.core.infrastructure.repository.outbox;

import com.core.infrastructure.entity.OutboxMessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxCrudRepository extends CrudRepository<OutboxMessageEntity, UUID> {
	List<OutboxMessageEntity> findByProcessed(boolean processed);
}
