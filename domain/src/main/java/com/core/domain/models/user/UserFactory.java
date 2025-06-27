package com.core.domain.models.user;

public class UserFactory implements IUserFactory {

	/**
	 * Creates a new User with the given username, email and fullname.
	 *
	 * @param username       The string value of the username.
	 * @param password       The string value of the password.
	 * @param email 		 The string value of the email.
	 * @param fullName 		 The string value of the fullName.
	 * @param address 		 The string value of the address.
	 * @return A new User instance.
	 * @throws IllegalArgumentException if username, email or fullName is null or invalid.
	 */
	@Override
	public User create(String username, String password, String email, String fullName, String address, String role) {
		if (username == null) {
			throw new IllegalArgumentException("username is required and cannot be null.");
		}
		if (password == null) {
			throw new IllegalArgumentException("password is required and cannot be null.");
		}
		if (email == null) {
			throw new IllegalArgumentException("email is required and cannot be null.");
		}
		if (fullName == null) {
			throw new IllegalArgumentException("fullName is required and cannot be null.");
		}
		if (address == null) {
			throw new IllegalArgumentException("address is required and cannot be null.");
		}
		if (role == null) {
			throw new IllegalArgumentException("role is required and cannot be null.");
		}
		return new User(username, password, email, fullName, address, role);
	}
}
