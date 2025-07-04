package com.core.testingmodule.application.user;

import com.core.application.user.login.LoginUserCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginUserCommandTest {

    @Test
    void testCanInstantiateAndAccessProperties() {
        // Arrange
        String email = "test@example.com";
        String password = "securePassword";

        // Act
        LoginUserCommand command = new LoginUserCommand(email, password);

        // Assert
        assertThat(command).isNotNull();
        assertThat(command.getEmail()).isEqualTo(email);
        assertThat(command.getPassword()).isEqualTo(password);
    }
}
