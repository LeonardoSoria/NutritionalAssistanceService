package com.core.testingmodule.application.appointment.getAppointments;

import com.core.application.appointment.getAppointments.GetAppointmentsQuery;
import com.core.application.appointment.getAppointments.GetAppointmentsHandler;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAppointmentsHandlerTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    private GetAppointmentsHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new GetAppointmentsHandler(appointmentRepository);
    }

    @Test
    void testHandleReturnsAppointmentsForNutritionist() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        DateValue dateValue = new DateValue(LocalDate.now());
        Appointment appointment1 = new Appointment(clientId, nutritionistId, dateValue);
        Appointment appointment2 = new Appointment(clientId, nutritionistId, dateValue);
        List<Appointment> expectedAppointments = List.of(appointment1, appointment2);

        when(appointmentRepository.findByNutritionistId(nutritionistId)).thenReturn(expectedAppointments);

        GetAppointmentsQuery query = new GetAppointmentsQuery(nutritionistId);

        // Act
        List<Appointment> result = handler.handle(query);

        // Assert
        verify(appointmentRepository).findByNutritionistId(nutritionistId);
        assertEquals(expectedAppointments, result);
    }
}
