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
        UUID nutritionistId = UUID.randomUUID();
        DateValue appointmentDate = new DateValue(LocalDate.now());

        // Act
        CreateAppointmentCommand command = new CreateAppointmentCommand(clientId, nutritionistId, appointmentDate);

        // Assert
        assertEquals(clientId, command.getClientId());
        assertEquals(nutritionistId, command.getNutritionistId());
        assertEquals(appointmentDate, command.getAppointmentDate());
    }
}
