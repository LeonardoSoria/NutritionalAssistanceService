package com.core.testingmodule.application.user;

import com.core.application.user.login.dto.LoginDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LoginDtoTest {

    @Test
    void testBuilderAndAccessors() {
        UUID userId = UUID.randomUUID();
        LoginDto dto = LoginDto.builder()
                .token("my-token")
                .tokenType("Bearer")
                .expiresIn(3600)
                .userId(userId)
                .username("testuser")
                .email("test@example.com")
                .role("user")
                .build();

        assertThat(dto.getToken()).isEqualTo("my-token");
        assertThat(dto.getTokenType()).isEqualTo("Bearer");
        assertThat(dto.getExpiresIn()).isEqualTo(3600);
        assertThat(dto.getUserId()).isEqualTo(userId);
        assertThat(dto.getUsername()).isEqualTo("testuser");
        assertThat(dto.getEmail()).isEqualTo("test@example.com");
        assertThat(dto.getRole()).isEqualTo("user");

        // also test setters
        dto.setToken("new-token");
        assertThat(dto.getToken()).isEqualTo("new-token");
    }

    @Test
    void testNoArgsConstructor() {
        LoginDto dto = new LoginDto();
        dto.setToken("some-token");
        dto.setTokenType("Bearer");
        dto.setExpiresIn(1200);
        dto.setUserId(UUID.randomUUID());
        dto.setUsername("tester");
        dto.setEmail("tester@example.com");
        dto.setRole("admin");

        assertThat(dto.getToken()).isEqualTo("some-token");
        assertThat(dto.getTokenType()).isEqualTo("Bearer");
        assertThat(dto.getExpiresIn()).isEqualTo(1200);
        assertThat(dto.getUsername()).isEqualTo("tester");
        assertThat(dto.getEmail()).isEqualTo("tester@example.com");
        assertThat(dto.getRole()).isEqualTo("admin");
    }
}
