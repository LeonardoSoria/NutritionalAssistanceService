package com.core.testingmodule.infrastructure.user;

import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.UserEntity;
import com.core.infrastructure.mapper.UserPersistenceMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserPersistenceMapperTest {

    @Test
    void testToDomainModel() {
        UUID id = UUID.randomUUID();
        UserEntity entity = UserEntity.builder()
                .id(id)
                .username("testuser")
                .password("password")
                .email("test@example.com")
                .fullName("Test User")
                .address("123 Main St")
                .role("ADMIN")
                .createdAt("2024-01-01")
                .build();

        User domain = UserPersistenceMapper.toDomainModel(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(id);
        assertThat(domain.getUsername()).isEqualTo("testuser");
        assertThat(domain.getPassword()).isEqualTo("password");
        assertThat(domain.getEmail()).isEqualTo("test@example.com");
        assertThat(domain.getFullName()).isEqualTo("Test User");
        assertThat(domain.getAddress()).isEqualTo("123 Main St");
        assertThat(domain.getRole()).isEqualTo("ADMIN");
        assertThat(domain.getCreatedAt()).isEqualTo(DateValue.from(LocalDate.of(2024, 1, 1)));
    }

    @Test
    void testToEntity() {
        UUID id = UUID.randomUUID();
        User domain = new User(
                id,
                "testuser",
                "password",
                "test@example.com",
                "Test User",
                "123 Main St",
                "ADMIN",
                DateValue.from(LocalDate.of(2024, 01, 01))
        );

        UserEntity entity = UserPersistenceMapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getUsername()).isEqualTo("testuser");
        assertThat(entity.getPassword()).isEqualTo("password");
        assertThat(entity.getEmail()).isEqualTo("test@example.com");
        assertThat(entity.getFullName()).isEqualTo("Test User");
        assertThat(entity.getAddress()).isEqualTo("123 Main St");
        assertThat(entity.getRole()).isEqualTo("ADMIN");
        assertThat(entity.getCreatedAt()).isEqualTo("2024-01-01");
    }
}
