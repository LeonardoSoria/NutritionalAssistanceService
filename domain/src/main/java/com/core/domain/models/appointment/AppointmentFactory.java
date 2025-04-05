package com.core.domain.models.appointment;

import com.core.domain.shared.DateValue;

import java.util.UUID;

public class AppointmentFactory implements IAppointmentFactory{

    /**
     * Creates a new Appointment with the given client ID and date.
     *
     * @param clientId The ID of the client.
     * @param date     The date of the appointment.
     * @return A new Appointment instance.
     * @throws IllegalArgumentException if clientId or date is null or invalid.
     */
    @Override
    public Appointment create(UUID clientId, DateValue date) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID must be a positive non-null value.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date value is required.");
        }
        return new Appointment(clientId, date);
    }
}