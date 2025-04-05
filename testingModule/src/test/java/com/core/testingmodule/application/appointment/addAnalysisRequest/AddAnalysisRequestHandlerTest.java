package com.core.testingmodule.application.appointment.addAnalysisRequest;

import com.core.application.appointment.addAnalysisRequest.AddAnalysisRequestCommand;
import com.core.application.appointment.addAnalysisRequest.AddAnalysisRequestHandler;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.shared.DateValue;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddAnalysisRequestHandlerTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    private AddAnalysisRequestHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new AddAnalysisRequestHandler(appointmentRepository);
    }

    @Test
    void testHandleAppointmentNotFound() {
        // Arrange
        UUID appointmentId = UUID.randomUUID();
        DateValue requestedDate = new DateValue(LocalDate.now());
        AddAnalysisRequestCommand command = new AddAnalysisRequestCommand(appointmentId, requestedDate);

        // Act & Assert
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            handler.handle(command);
        });

        assertEquals("Appointment not found with ID: " + appointmentId, exception.getMessage());
    }

    @Test
    void testHandleAddsAnalysisRequestAndUpdatesAppointment() {
        // Arrange
        UUID appointmentId = UUID.randomUUID();
        DateValue requestedDate = new DateValue(LocalDate.now());
        AddAnalysisRequestCommand command = new AddAnalysisRequestCommand(appointmentId, requestedDate);
        Appointment mockAppointment = mock(Appointment.class);

        // Act
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(mockAppointment));

        Appointment result = handler.handle(command);

        // Assert
        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository).update(captor.capture());

        Appointment capturedAppointment = captor.getValue();
        verify(mockAppointment).addAnalysisRequest(requestedDate);

        assertEquals(mockAppointment, result);
    }
}
