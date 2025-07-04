package com.core.testingmodule.infrastructure.user;

import com.core.infrastructure.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

	@Test
	void testEqualsAndHashCode() {
		UUID id = UUID.randomUUID();
		UserEntity user1 = new UserEntity(id, "user1", "pass", "email@example.com", "Full Name", "Address", "Role", "2025-07-04");
		UserEntity user2 = new UserEntity(id, "user1", "pass", "email@example.com", "Full Name", "Address", "Role", "2025-07-04");

		// equals should be true for same id
		assertEquals(user1, user2);
		assertEquals(user1.hashCode(), user2.hashCode());

		// equals false for different id
		UserEntity user3 = new UserEntity(UUID.randomUUID(), "user1", "pass", "email@example.com", "Full Name", "Address", "Role", "2025-07-04");
		assertNotEquals(user1, user3);
	}

	@Test
	void testToString() {
		UserEntity user = new UserEntity(UUID.randomUUID(), "user", "pass", "email@example.com", "Full Name", "Address", "Role", "2025-07-04");
		String toString = user.toString();
		assertTrue(toString.contains("user"));
		assertTrue(toString.contains("email@example.com"));
	}

	@Test
	void testGettersAndSetters() {
		UserEntity user = new UserEntity();
		UUID id = UUID.randomUUID();

		user.setId(id);
		user.setUsername("testUser");
		user.setPassword("testPass");
		user.setEmail("test@email.com");
		user.setFullName("Test User");
		user.setAddress("Test Address");
		user.setRole("USER");
		user.setCreatedAt("2025-07-04");

		assertEquals(id, user.getId());
		assertEquals("testUser", user.getUsername());
		assertEquals("testPass", user.getPassword());
		assertEquals("test@email.com", user.getEmail());
		assertEquals("Test User", user.getFullName());
		assertEquals("Test Address", user.getAddress());
		assertEquals("USER", user.getRole());
		assertEquals("2025-07-04", user.getCreatedAt());
	}
}
