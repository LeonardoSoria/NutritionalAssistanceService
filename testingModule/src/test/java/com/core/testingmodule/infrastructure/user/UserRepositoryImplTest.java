package com.core.testingmodule.infrastructure.user;

import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.UserEntity;
import com.core.infrastructure.repository.user.UserCrudRepository;
import com.core.infrastructure.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserRepositoryImplTest {

    @Mock
    private UserCrudRepository userCrudRepository;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    private UserEntity userEntity;
    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();

        userEntity = UserEntity.builder()
                .id(userId)
                .username("testuser")
                .password("password")
                .email("test@example.com")
                .fullName("Test User")
                .address("123 Street")
                .role("ADMIN")
                .createdAt("2024-01-01")
                .build();

        user = new User(
                userId,
                "testuser",
                "password",
                "test@example.com",
                "Test User",
                "123 Street",
                "ADMIN",
                DateValue.from(LocalDate.of(2024, 1, 1))
        );
    }

    @Test
    void testFindById_found() {
        when(userCrudRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<User> result = userRepository.findById(userId);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(userId);
        verify(userCrudRepository, times(1)).findById(userId);
    }

    @Test
    void testFindById_notFound() {
        when(userCrudRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findById(userId);

        assertThat(result).isEmpty();
        verify(userCrudRepository, times(1)).findById(userId);
    }

    @Test
    void testFindUsers() {
        when(userCrudRepository.findAll()).thenReturn(List.of(userEntity));

        List<User> result = userRepository.findUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("testuser");
        verify(userCrudRepository, times(1)).findAll();
    }

    @Test
    void testLogin_success() {
        when(userCrudRepository.findByEmail("test@example.com")).thenReturn(List.of(userEntity));

        User result = userRepository.login("test@example.com", "password");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(userCrudRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testLogin_wrongPassword() {
        when(userCrudRepository.findByEmail("test@example.com")).thenReturn(List.of(userEntity));

        User result = userRepository.login("test@example.com", "wrongpassword");

        assertThat(result).isNull();
        verify(userCrudRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testLogin_userNotFound() {
        when(userCrudRepository.findByEmail("test@example.com")).thenReturn(Collections.emptyList());

        User result = userRepository.login("test@example.com", "password");

        assertThat(result).isNull();
        verify(userCrudRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testUpsert() {
        when(userCrudRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User result = userRepository.upsert(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userCrudRepository, times(1)).save(any(UserEntity.class));
    }
}
