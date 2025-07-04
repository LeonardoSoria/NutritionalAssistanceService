package com.core.testingmodule.domain.token;

import com.core.domain.models.auth.Token;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenTest {

    @Test
    void testTokenGetters() {
        Token token = new Token("abc123", "Bearer", 3600);

        assertThat(token.getToken()).isEqualTo("abc123");
        assertThat(token.getType()).isEqualTo("Bearer");
        assertThat(token.getExpiresIn()).isEqualTo(3600);
    }
}
