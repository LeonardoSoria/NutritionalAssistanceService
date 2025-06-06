package com.core.testingmodule.application.appointment.createAppointment;

import com.core.application.appointment.createAppointment.CreateAppointmentCommand;
import com.core.application.appointment.createAppointment.CreateAppointmentHandler;
import com.core.application.outbox.OutboxService;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateAppointmentHandlerTest {

    @Mock
    private IAppointmentRepository appointmentRepository;
	@Mock
	private OutboxService outboxService;

    private CreateAppointmentHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new CreateAppointmentHandler(appointmentRepository, outboxService);
    }

    @Test
    void testHandleCreatesAndUpdatesAppointment() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        DateValue appointmentDate = new DateValue(LocalDate.now());  // Example appointment date

        CreateAppointmentCommand command = new CreateAppointmentCommand(clientId, appointmentDate);

        // Act
        Appointment result = handler.handle(command);

        // Assert
        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository).update(captor.capture());

        Appointment capturedAppointment = captor.getValue();

        // Verify that the captured Appointment contains the expected data
        assertEquals(clientId, capturedAppointment.getClientId());
        assertEquals(appointmentDate, capturedAppointment.getDate());

        // Verify the returned Appointment is the same instance
        assertEquals(capturedAppointment, result);
    }
}
