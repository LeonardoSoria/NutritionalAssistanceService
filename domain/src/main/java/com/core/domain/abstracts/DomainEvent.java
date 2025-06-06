package com.core.domain.abstracts;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID id;
    private final LocalDateTime occurredOn;

    public DomainEvent() {
        this.id = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

	public abstract String getEventType();
}
