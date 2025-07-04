package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.AppointmentFactory;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentFactoryTest {

    private AppointmentFactory appointmentFactory;

    @BeforeEach
    void setUp() {
        appointmentFactory = new AppointmentFactory();
    }

    @Test
    void testCreate_ValidClientIdAndDate_ShouldCreateAppointment() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        DateValue date = new DateValue(LocalDate.of(2025, 2, 19));

        // Act
        Appointment appointment = appointmentFactory.create(clientId, nutritionistId, date);

        // Assert
        assertNotNull(appointment);
        assertEquals(clientId, appointment.getClientId());
        assertEquals(date, appointment.getDate());
    }

    @Test
    void testCreate_NullClientId_ShouldThrowIllegalArgumentException() {
        // Arrange
        UUID clientId = null;
        UUID nutritionistId = null;
        DateValue date = new DateValue(LocalDate.of(2025, 2, 19));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentFactory.create(clientId, nutritionistId, date);
        });
        assertEquals("Client ID must be a positive non-null value.", exception.getMessage());
    }

    @Test
    void testCreate_NullDate_ShouldThrowIllegalArgumentException() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        DateValue date = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentFactory.create(clientId, nutritionistId, date);
        });
        assertEquals("Date value is required.", exception.getMessage());
    }

    @Test
    void testCreate_NullClientIdAndNullDate_ShouldThrowIllegalArgumentException() {
        // Arrange
        UUID clientId = null;
        UUID nutritionistId = null;
        DateValue date = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentFactory.create(clientId, nutritionistId, date);
        });
        assertEquals("Client ID must be a positive non-null value.", exception.getMessage());
    }
}
