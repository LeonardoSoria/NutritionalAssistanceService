package com.core.testingmodule.application.nutritionalPlan;

import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanHandler;
import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanQuery;
import com.core.application.nutritionalPlan.getNutritionalPlan.dto.NutritionalPlanDto;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
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

class GetNutritionalPlanHandlerTest {

	@Mock
	private INutritionalPlanRepository nutritionalPlanRepository;

	@Mock
	private IUserRepository userRepository;

	private GetNutritionalPlanHandler handler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		handler = new GetNutritionalPlanHandler(nutritionalPlanRepository, userRepository);
	}

	@Test
	void testHandle_ReturnsNutritionalPlanDtos() {
		// Arrange
		UUID nutritionistId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		UUID analysisResultId = UUID.randomUUID();

		GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(nutritionistId);

		// Mock NutritionalPlan
		NutritionalPlan plan = new NutritionalPlan(
			UUID.randomUUID(),
			clientId,
			nutritionistId,
			"sample plan details",
			List.of(analysisResultId),
			true
		);

		when(nutritionalPlanRepository.findByNutritionistId(nutritionistId))
			.thenReturn(List.of(plan));

		// Mock Users
		User mockClient = new User(clientId, "JohnDoe", "sesame", "mail", "JohnDoe",
			"Cotoca", "client", DateValue.from(LocalDate.now()));
		User mockNutritionist = new User(nutritionistId, "PamelaDoe", "sesame", "mail", "PamelaDoe",
			"Cotoca", "nutritionist", DateValue.from(LocalDate.now()));

		when(userRepository.findById(clientId)).thenReturn(Optional.of(mockClient));
		when(userRepository.findById(nutritionistId)).thenReturn(Optional.of(mockNutritionist));

		// Act
		List<NutritionalPlanDto> result = handler.handle(query);

		// Assert
		assertEquals(1, result.size());
		NutritionalPlanDto dto = result.get(0);

		assertEquals(plan.getId().toString(), dto.getId());
		assertEquals(clientId.toString(), dto.getClientId());
		assertEquals("JohnDoe", dto.getClientName());
		assertEquals(nutritionistId.toString(), dto.getNutritionistId());
		assertEquals("PamelaDoe", dto.getNutritionistName());
		assertEquals(List.of(analysisResultId.toString()), dto.getAnalysisResults());
		assertTrue(dto.isDelivered());
		assertEquals("sample plan details", dto.getPlanDetails());

		// Verify repository interactions
		verify(nutritionalPlanRepository).findByNutritionistId(nutritionistId);
		verify(userRepository).findById(clientId);
		verify(userRepository).findById(nutritionistId);
	}

	@Test
	void testHandle_ReturnsEmptyList_WhenNoPlansFound() {
		// Arrange
		UUID nutritionistId = UUID.randomUUID();
		GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(nutritionistId);

		when(nutritionalPlanRepository.findByNutritionistId(nutritionistId)).thenReturn(List.of());

		// Act
		List<NutritionalPlanDto> result = handler.handle(query);

		// Assert
		assertTrue(result.isEmpty());

		// Verify repository interactions
		verify(nutritionalPlanRepository).findByNutritionistId(nutritionistId);
		verifyNoInteractions(userRepository);
	}

	@Test
	void testHandle_ReturnsNullClientOrNutritionistName_WhenUserNotFound() {
		// Arrange
		UUID nutritionistId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();

		NutritionalPlan plan = new NutritionalPlan(
			UUID.randomUUID(),
			clientId,
			nutritionistId,
			"Details",
			List.of(),
			false
		);

		when(nutritionalPlanRepository.findByNutritionistId(nutritionistId)).thenReturn(List.of(plan));
		when(userRepository.findById(clientId)).thenReturn(Optional.empty());
		when(userRepository.findById(nutritionistId)).thenReturn(Optional.empty());

		GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(nutritionistId);

		// Act
		List<NutritionalPlanDto> result = handler.handle(query);

		// Assert
		assertEquals(1, result.size());
		NutritionalPlanDto dto = result.get(0);
		assertNull(dto.getClientName());
		assertNull(dto.getNutritionistName());
	}
}
