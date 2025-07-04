package com.core.testingmodule.application.user;

import com.core.application.user.login.dto.TokenDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenDtoTest {

    @Test
    void testBuilderAndAccessors() {
        TokenDto dto = TokenDto.builder()
                .accessToken("my-access-token")
                .tokenType("Bearer")
                .expiresIn(3600)
                .build();

        assertThat(dto.getAccessToken()).isEqualTo("my-access-token");
        assertThat(dto.getTokenType()).isEqualTo("Bearer");
        assertThat(dto.getExpiresIn()).isEqualTo(3600);

        // test setters
        dto.setAccessToken("new-token");
        assertThat(dto.getAccessToken()).isEqualTo("new-token");
    }

    @Test
    void testNoArgsConstructor() {
        TokenDto dto = new TokenDto();
        dto.setAccessToken("some-token");
        dto.setTokenType("Bearer");
        dto.setExpiresIn(1200);

        assertThat(dto.getAccessToken()).isEqualTo("some-token");
        assertThat(dto.getTokenType()).isEqualTo("Bearer");
        assertThat(dto.getExpiresIn()).isEqualTo(1200);
    }
}
