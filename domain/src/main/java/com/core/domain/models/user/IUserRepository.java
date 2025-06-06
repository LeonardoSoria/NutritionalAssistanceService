package com.core.domain.models.user;

import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    /**
     * Finds an appointment by its ID.
     *
     * @param id The UUID of the appointment.
     * @return An Optional containing the user if found, otherwise empty.
     */
    Optional<User> findById(UUID id);

    /**
     * Finds an appointment by its ID.
     *
     * @param username The string value of the username.
     * @return A List containing the users if found, otherwise empty.
     */
    List<User> findByUsername(String username);

	/**
	 * Saves or Updates an existing User.
	 *
	 * @param user The User to save/update.
	 */
	User upsert(User user);
}
