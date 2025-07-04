package com.core.testingmodule.application.user;

import com.core.application.user.login.LoginUserCommand;
import com.core.application.user.login.LoginUserHandler;
import com.core.application.user.login.dto.LoginDto;
import com.core.domain.exceptions.InvalidCredentialsException;
import com.core.domain.models.auth.ITokenService;
import com.core.domain.models.auth.Token;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUserHandlerTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ITokenService tokenService;

    private LoginUserHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new LoginUserHandler(userRepository, tokenService);
    }

    @Test
    void testHandleSuccessfulLogin() {
        // Arrange
        String email = "test@example.com";
        String password = "securePassword";

        UUID userId = UUID.randomUUID();
        User user = new User(userId, "testUser", password, email, "Full Name", "Address", "user", DateValue.from(LocalDate.now()));
        Token token = new Token("accessToken", "Bearer", 3600);

        when(userRepository.login(email, password)).thenReturn(user);
        when(tokenService.getToken()).thenReturn(token);

        LoginUserCommand command = new LoginUserCommand(email, password);

        // Act
        LoginDto result = handler.handle(command);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo("accessToken");
        assertThat(result.getTokenType()).isEqualTo("Bearer");
        assertThat(result.getExpiresIn()).isEqualTo(3600);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getUsername()).isEqualTo("testUser");
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getRole()).isEqualTo("user");

        verify(userRepository, times(1)).login(email, password);
        verify(tokenService, times(1)).getToken();
    }

    @Test
    void testHandleInvalidCredentialsThrows() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongPassword";
        when(userRepository.login(email, password)).thenReturn(null);

        LoginUserCommand command = new LoginUserCommand(email, password);

        // Act & Assert
        assertThatThrownBy(() -> handler.handle(command))
                .isInstanceOf(InvalidCredentialsException.class)
                .hasMessage("Invalid email or password.");

        verify(userRepository, times(1)).login(email, password);
        verify(tokenService, never()).getToken();
    }
}
