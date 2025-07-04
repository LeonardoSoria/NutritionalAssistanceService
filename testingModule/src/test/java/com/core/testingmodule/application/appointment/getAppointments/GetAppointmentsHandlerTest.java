package com.core.testingmodule.application.appointment.getAppointments;

import com.core.application.appointment.getAppointments.GetAppointmentsHandler;
import com.core.application.appointment.getAppointments.GetAppointmentsQuery;
import com.core.application.appointment.getAppointments.dto.AppointmentDto;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAppointmentsHandlerTest {

	@Mock
	private IAppointmentRepository appointmentRepository;

	@Mock
	private IUserRepository userRepository;

	private GetAppointmentsHandler handler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		handler = new GetAppointmentsHandler(appointmentRepository, userRepository);
	}

	@Test
	void testHandleReturnsAppointmentsForNutritionist() {
		// Arrange
		UUID clientId = UUID.randomUUID();
		UUID nutritionistId = UUID.randomUUID();
		UUID appointmentId = UUID.randomUUID();
		DateValue dateValue = new DateValue(LocalDate.now());

		Appointment appointment1 = new Appointment(
			appointmentId,
			clientId,
			nutritionistId,
			dateValue,
			"CONFIRMED",
			List.of()
		);

		List<Appointment> expectedAppointments = List.of(appointment1);

		when(appointmentRepository.findByNutritionistId(nutritionistId)).thenReturn(expectedAppointments);

		// Mock Users
		User client = new User(clientId, "JohnDoe", "sesame", "mail", "JohnDoe",
			"Cotoca", "client", DateValue.from(LocalDate.now()));
		User nutritionist = new User(nutritionistId, "PamelaDoe", "sesame", "mail", "PamelaDoe",
			"Cotoca", "nutritionist", DateValue.from(LocalDate.now()));
		when(userRepository.findById(clientId)).thenReturn(Optional.of(client));
		when(userRepository.findById(nutritionistId)).thenReturn(Optional.of(nutritionist));

		GetAppointmentsQuery query = new GetAppointmentsQuery(nutritionistId);

		// Act
		List<AppointmentDto> result = handler.handle(query);

		// Assert
		assertEquals(1, result.size());
		AppointmentDto dto = result.get(0);

		assertEquals(appointmentId.toString(), dto.getId());
		assertEquals(clientId.toString(), dto.getClientId());
		assertEquals("JohnDoe", dto.getClientName());
		assertEquals(nutritionistId.toString(), dto.getNutritionistId());
		assertEquals("PamelaDoe", dto.getNutritionistName());
		assertEquals(dateValue.toLocalDate(), dto.getDate());
		assertEquals("CONFIRMED", dto.getStatus());
		assertTrue(dto.getAnalysisRequestResponses().isEmpty());

		verify(appointmentRepository).findByNutritionistId(nutritionistId);
		verify(userRepository).findById(clientId);
		verify(userRepository).findById(nutritionistId);
	}

	@Test
	void testHandleReturnsEmptyListWhenNoAppointmentsFound() {
		// Arrange
		UUID nutritionistId = UUID.randomUUID();
		GetAppointmentsQuery query = new GetAppointmentsQuery(nutritionistId);

		when(appointmentRepository.findByNutritionistId(nutritionistId)).thenReturn(List.of());

		// Act
		List<AppointmentDto> result = handler.handle(query);

		// Assert
		assertTrue(result.isEmpty());
		verify(appointmentRepository).findByNutritionistId(nutritionistId);
		verifyNoInteractions(userRepository);
	}

	@Test
	void testHandleReturnsNullNamesWhenUserNotFound() {
		// Arrange
		UUID clientId = UUID.randomUUID();
		UUID nutritionistId = UUID.randomUUID();
		UUID appointmentId = UUID.randomUUID();
		DateValue dateValue = new DateValue(LocalDate.now());

		Appointment appointment = new Appointment(
			appointmentId,
			clientId,
			nutritionistId,
			dateValue,
			"PENDING",
			List.of()
		);

		when(appointmentRepository.findByNutritionistId(nutritionistId)).thenReturn(List.of(appointment));
		when(userRepository.findById(clientId)).thenReturn(Optional.empty());
		when(userRepository.findById(nutritionistId)).thenReturn(Optional.empty());

		GetAppointmentsQuery query = new GetAppointmentsQuery(nutritionistId);

		// Act
		List<AppointmentDto> result = handler.handle(query);

		// Assert
		assertEquals(1, result.size());
		AppointmentDto dto = result.get(0);

		assertNull(dto.getClientName());
		assertNull(dto.getNutritionistName());
	}
}
