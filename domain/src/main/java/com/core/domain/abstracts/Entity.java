package com.core.domain.abstracts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class Entity {
    public UUID id;
    public final List<DomainEvent> domainEvents;

    protected Entity() {
        id = UUID.randomUUID();
        domainEvents = new ArrayList<>();
    }

    public void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    public UUID getId() {
        return id;
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
