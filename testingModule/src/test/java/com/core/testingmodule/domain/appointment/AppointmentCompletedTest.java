package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.events.AppointmentCompleted;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentCompletedTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        UUID expectedAppointmentId = UUID.randomUUID();

        // Act
        AppointmentCompleted appointmentCompleted = new AppointmentCompleted(expectedAppointmentId);

        // Assert
        assertEquals(expectedAppointmentId, appointmentCompleted.getAppointmentId(), "The appointment ID should be correctly set and retrieved.");
    }
}
