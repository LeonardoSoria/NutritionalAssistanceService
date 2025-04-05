package com.core.testingmodule.application.nutritionalPlan;

import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanHandler;
import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanQuery;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetNutritionalPlanHandlerTest {

    @Mock
    private INutritionalPlanRepository nutritionalPlanRepository;

    private GetNutritionalPlanHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new GetNutritionalPlanHandler(nutritionalPlanRepository);
    }

    @Test
    void testHandle_ReturnsNutritionalPlans() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(clientId);

        // Mock the repository to return a list of NutritionalPlans
        NutritionalPlan plan1 = new NutritionalPlan(clientId, nutritionistId, "Plan 1");
        NutritionalPlan plan2 = new NutritionalPlan(clientId, nutritionistId, "Plan 2");
        List<NutritionalPlan> expectedPlans = List.of(plan1, plan2);
        when(nutritionalPlanRepository.findByClientId(clientId)).thenReturn(expectedPlans);

        // Act
        List<NutritionalPlan> result = handler.handle(query);

        // Assert
        assertEquals(expectedPlans, result, "The returned nutritional plans should match the mock.");

        // Verify that the repository method was called with the correct clientId
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(nutritionalPlanRepository).findByClientId(captor.capture());
        assertEquals(clientId, captor.getValue(), "The repository should be called with the correct clientId.");
    }

    @Test
    void testHandle_ReturnsEmptyList_WhenNoPlansFound() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(clientId);

        // Mock the repository to return an empty list
        when(nutritionalPlanRepository.findByClientId(clientId)).thenReturn(List.of());

        // Act
        List<NutritionalPlan> result = handler.handle(query);

        // Assert
        assertEquals(0, result.size(), "The result should be an empty list when no plans are found.");

        // Verify that the repository method was called with the correct clientId
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(nutritionalPlanRepository).findByClientId(captor.capture());
        assertEquals(clientId, captor.getValue(), "The repository should be called with the correct clientId.");
    }
}
