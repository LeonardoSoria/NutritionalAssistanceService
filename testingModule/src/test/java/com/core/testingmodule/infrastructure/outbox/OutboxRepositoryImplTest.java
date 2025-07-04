package com.core.testingmodule.infrastructure.outbox;

import com.core.domain.models.outbox.OutboxMessage;
import com.core.infrastructure.entity.OutboxMessageEntity;
import com.core.infrastructure.repository.outbox.OutboxCrudRepository;
import com.core.infrastructure.repository.outbox.OutboxRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OutboxRepositoryImplTest {

    @Mock
    private OutboxCrudRepository outboxCrudRepository;

    @InjectMocks
    private OutboxRepositoryImpl outboxRepository;

    private OutboxMessage outboxMessage;
    private OutboxMessageEntity outboxMessageEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        outboxMessage = new OutboxMessage(
                id,
                "UserCreated",
                "1.0",
                "{ \"some\": \"payload\" }",
                now,
                "sourceApp",
                false
        );

        outboxMessageEntity = new OutboxMessageEntity(
                id,
                "UserCreated",
                "1.0",
                "{ \"some\": \"payload\" }",
                now,
                "sourceApp",
                false
        );
    }

    @Test
    void testSave() {
        outboxRepository.save(outboxMessage);

        verify(outboxCrudRepository, times(1)).save(any(OutboxMessageEntity.class));
    }

    @Test
    void testFindByProcessed() {
        when(outboxCrudRepository.findByProcessed(false)).thenReturn(List.of(outboxMessageEntity));

        List<OutboxMessage> result = outboxRepository.findByProcessed(false);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEventType()).isEqualTo("UserCreated");
        assertThat(result.get(0).isProcessed()).isFalse();

        verify(outboxCrudRepository, times(1)).findByProcessed(false);
    }
}
