package com.core.testingmodule.application.user;

import com.core.application.outbox.OutboxService;
import com.core.application.user.create.CreateUserCommand;
import com.core.application.user.create.CreateUserHandler;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateUserHandlerTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private OutboxService outboxService;

    private CreateUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new CreateUserHandler(userRepository, outboxService);
    }

    @Test
    void testHandleCreatesAndPersistsUser() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "USER"
        );

        // Act
        User result = handler.handle(command);

        // Assert
        // Capture the user passed to repository
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).upsert(captor.capture());

        User capturedUser = captor.getValue();
        assertThat(capturedUser.getUsername()).isEqualTo(command.getUsername());
        assertThat(capturedUser.getPassword()).isEqualTo(command.getPassword());
        assertThat(capturedUser.getEmail()).isEqualTo(command.getEmail());
        assertThat(capturedUser.getFullName()).isEqualTo(command.getFullName());
        assertThat(capturedUser.getAddress()).isEqualTo(command.getAddress());
        assertThat(capturedUser.getRole()).isEqualTo(command.getRole());

        // Outbox events
        verify(outboxService, atLeastOnce()).recordEvent(eq("USER_CREATED"), any());

        // Domain events cleared
        assertThat(result.getDomainEvents()).isEmpty();
    }
}
