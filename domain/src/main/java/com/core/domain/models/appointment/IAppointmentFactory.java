package com.core.domain.models.appointment;

import com.core.domain.shared.DateValue;

import java.util.UUID;

public interface IAppointmentFactory {
    Appointment create(UUID clientId, UUID nutritionistId, DateValue date);
}
