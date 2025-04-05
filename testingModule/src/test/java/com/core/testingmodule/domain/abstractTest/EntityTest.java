package com.core.testingmodule.domain.abstractTest;

import static org.junit.jupiter.api.Assertions.*;

import com.core.domain.abstracts.DomainEvent;
import com.core.domain.abstracts.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.UUID;

class EntityTest {

    private Entity entity;

    // Concrete class for testing (since Entity is abstract)
    static class TestEntity extends Entity {}

    @BeforeEach
    void setUp() {
        entity = new TestEntity();
    }

    @Test
    void shouldGenerateUniqueIdOnCreation() {
        assertNotNull(entity.getId(), "ID should be generated on creation");
        assertTrue(entity.getId() instanceof UUID, "ID should be of type UUID");
    }

    @Test
    void shouldAddDomainEvent() {
        DomainEvent mockEvent = Mockito.mock(DomainEvent.class);
        entity.addDomainEvent(mockEvent);

        assertEquals(1, entity.getDomainEvents().size(), "Domain event should be added");
        assertEquals(mockEvent, entity.getDomainEvents().get(0), "Event should match the added event");
    }

    @Test
    void shouldClearDomainEvents() {
        DomainEvent mockEvent = Mockito.mock(DomainEvent.class);
        entity.addDomainEvent(mockEvent);
        entity.clearDomainEvents();

        assertTrue(entity.getDomainEvents().isEmpty(), "Domain events should be cleared");
    }

    @Test
    void shouldReturnImmutableDomainEventsList() {
        DomainEvent mockEvent = Mockito.mock(DomainEvent.class);
        entity.addDomainEvent(mockEvent);

        List<DomainEvent> events = entity.getDomainEvents();
        assertThrows(UnsupportedOperationException.class, () -> events.add(Mockito.mock(DomainEvent.class)),
                "Returned list should be immutable");
    }
}
