package com.core.testingmodule.application.user;

import com.core.application.user.getUser.GetUsersHandler;
import com.core.application.user.getUser.GetUsersQuery;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetUsersHandlerTest {

	@Mock
	private IUserRepository userRepository;

	private GetUsersHandler handler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		handler = new GetUsersHandler(userRepository);
	}

	@Test
	void testHandleReturnsListOfUsers() {
		// Arrange
		User user1 = new User(UUID.randomUUID(), "user1", "pass", "u1@example.com", "User One", "Address 1", "USER", DateValue.from(LocalDate.now()));
		User user2 = new User(UUID.randomUUID(), "user2", "pass", "u2@example.com", "User Two", "Address 2", "USER", DateValue.from(LocalDate.now()));

		when(userRepository.findUsers()).thenReturn(List.of(user1, user2));

		GetUsersQuery query = new GetUsersQuery();

		// Act
		List<User> result = handler.handle(query);

		// Assert
		assertThat(result).hasSize(2);
		assertThat(result).containsExactly(user1, user2);

		verify(userRepository, times(1)).findUsers();
	}
}
