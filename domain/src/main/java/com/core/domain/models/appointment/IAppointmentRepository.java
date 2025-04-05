package com.core.domain.models.appointment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAppointmentRepository {

    /**
     * Finds an appointment by its ID.
     *
     * @param id The UUID of the appointment.
     * @return An Optional containing the appointment if found, otherwise empty.
     */
    Optional<Appointment> findById(UUID id);

    /**
     * Finds an appointment by its ID.
     *
     * @param clientId The UUID of the clientId.
     * @return An Optional containing the appointment if found, otherwise empty.
     */
    List<Appointment> findByClientId(UUID clientId);

    /**
     * Updates an existing appointment.
     *
     * @param appointment The updated appointment object.
     */
    Appointment update(Appointment appointment);

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The UUID of the appointment to be deleted.
     */
    void deleteById(UUID id);
}
