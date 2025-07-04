package com.core.testingmodule.application.user;

import com.core.application.user.create.CreateUserCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserCommandTest {

    @Test
    void testCreateUserCommandGetters() {
        CreateUserCommand command = new CreateUserCommand(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "USER"
        );

        assertThat(command.getUsername()).isEqualTo("testuser");
        assertThat(command.getPassword()).isEqualTo("password123");
        assertThat(command.getEmail()).isEqualTo("test@example.com");
        assertThat(command.getFullName()).isEqualTo("Test User");
        assertThat(command.getAddress()).isEqualTo("123 Main St");
        assertThat(command.getRole()).isEqualTo("USER");
    }
}
