package com.core.domain.models.user;

public class UserFactory implements IUserFactory {

	/**
	 * Creates a new User with the given username, email and fullname.
	 *
	 * @param username       The string value of the username.
	 * @param email 		 The string value of the email.
	 * @param fullName 		 The string value of the fullName.
	 * @return A new User instance.
	 * @throws IllegalArgumentException if username, email or fullName is null or invalid.
	 */
	@Override
	public User create(String username, String email, String fullName) {
		if (username == null) {
			throw new IllegalArgumentException("username is required and cannot be null.");
		}
		if (email == null) {
			throw new IllegalArgumentException("email is required and cannot be null.");
		}
		if (fullName == null) {
			throw new IllegalArgumentException("fullName is required and cannot be null.");
		}
		return new User(username, email, fullName);
	}
}
