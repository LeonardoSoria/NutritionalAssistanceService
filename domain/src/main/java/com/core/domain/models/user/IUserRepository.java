package com.core.domain.models.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    /**
     * Finds a user by its ID.
     *
     * @param id The UUID of the user.
     * @return An Optional containing the user if found, otherwise empty.
     */
    Optional<User> findById(UUID id);

    /**
     * Finds a user by its ID.
     *
     * @return A List containing the users if found, otherwise empty.
     */
    List<User> findUsers();

	/**
	 * Finds an appointment by its ID.
	 *
	 * @param email The string value of the email.
	 * @param password The string value of the password.
	 * @return A List containing the users if found, otherwise empty.
	 */
	User login(String email, String password);

	/**
	 * Saves or Updates an existing User.
	 *
	 * @param user The User to save/update.
	 * @return A User updated or created.
	 */
	User upsert(User user);
}
