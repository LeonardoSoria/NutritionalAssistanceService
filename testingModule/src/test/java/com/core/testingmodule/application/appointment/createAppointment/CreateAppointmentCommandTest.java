package com.core.testingmodule.application.appointment.createAppointment;

import com.core.application.appointment.createAppointment.CreateAppointmentCommand;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateAppointmentCommandTest {

    @Test
    void testCreateAppointmentCommandConstructor() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        DateValue appointmentDate = new DateValue(LocalDate.now());

        // Act
        CreateAppointmentCommand command = new CreateAppointmentCommand(clientId, appointmentDate);

        // Assert
        assertEquals(clientId, command.getClientId());
        assertEquals(appointmentDate, command.getAppointmentDate());
    }
}
