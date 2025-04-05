package com.core.testingmodule.application.appointment.addAnalysisRequest;

import com.core.application.appointment.addAnalysisRequest.AddAnalysisRequestCommand;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddAnalysisRequestCommandTest {

    @Test
    void testCommandConstructor() {
        // Arrange
        UUID appointmentId = UUID.randomUUID();
        LocalDate requestedLocalDate = LocalDate.now();
        DateValue requestedDate = new DateValue(requestedLocalDate);

        // Act
        AddAnalysisRequestCommand command = new AddAnalysisRequestCommand(appointmentId, requestedDate);

        // Assert
        assertEquals(appointmentId, command.getAppointmentId(), "The appointmentId should be correctly set in the command.");
        assertEquals(requestedDate, command.getRequestedDate(), "The requestedDate should be correctly set in the command.");
    }
}
