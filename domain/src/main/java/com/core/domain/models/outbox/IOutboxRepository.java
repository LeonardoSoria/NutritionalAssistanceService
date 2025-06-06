package com.core.domain.models.outbox;

import java.util.List;

public interface IOutboxRepository {

    /**
     * Saves an existing outboxMessage.
     *
     * @param outboxMessage The saved outboxMessage object.
     */
    void save(OutboxMessage outboxMessage);

	/**
	 * Search an existing outboxMessage with processed value.
	 *
	 * @param processed The status of the outboxMessage object.
	 */
    List<OutboxMessage> findByProcessed(boolean processed);
}
