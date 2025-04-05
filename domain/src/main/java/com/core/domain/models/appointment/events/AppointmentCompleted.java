package com.core.domain.models.appointment.events;

import com.core.domain.abstracts.DomainEvent;

import java.util.UUID;

public class AppointmentCompleted extends DomainEvent {

    private final UUID appointmentId;

    public AppointmentCompleted(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }
}
