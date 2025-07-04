package com.core.testingmodule.domain.user;

import com.core.domain.models.user.User;
import com.core.domain.models.user.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserFactoryTest {

    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new UserFactory();
    }

    @Test
    void create_Success() {
        User user = userFactory.create(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN"
        );

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("testuser");
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getFullName()).isEqualTo("Test User");
        assertThat(user.getAddress()).isEqualTo("123 Main St");
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }

    @Test
    void create_ThrowsException_WhenUsernameIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                null,
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN"
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("username is required and cannot be null.");
    }

    @Test
    void create_ThrowsException_WhenPasswordIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                "testuser",
                null,
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN"
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("password is required and cannot be null.");
    }

    @Test
    void create_ThrowsException_WhenEmailIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                "testuser",
                "password123",
                null,
                "Test User",
                "123 Main St",
                "ADMIN"
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("email is required and cannot be null.");
    }

    @Test
    void create_ThrowsException_WhenFullNameIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                "testuser",
                "password123",
                "test@example.com",
                null,
                "123 Main St",
                "ADMIN"
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("fullName is required and cannot be null.");
    }

    @Test
    void create_ThrowsException_WhenAddressIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                null,
                "ADMIN"
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("address is required and cannot be null.");
    }

    @Test
    void create_ThrowsException_WhenRoleIsNull() {
        assertThatThrownBy(() -> userFactory.create(
                "testuser",
                "password123",
                "test@example.com",
                "Test User",
                "123 Main St",
                null
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("role is required and cannot be null.");
    }
}
