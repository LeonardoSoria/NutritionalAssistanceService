package com.core.testingmodule.application.nutritionalPlan;

import com.core.application.nutritionalPlan.createNutritionalPlan.CreateNutritionalPlanCommand;
import com.core.application.nutritionalPlan.createNutritionalPlan.CreateNutritionalPlanHandler;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateNutritionalPlanHandlerTest {

    @Mock
    private INutritionalPlanRepository nutritionalPlanRepository;

    private CreateNutritionalPlanHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new CreateNutritionalPlanHandler(nutritionalPlanRepository);
    }

    @Test
    void testHandleCreatesAndUpdatesNutritionalPlan() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "Plan details here";

        CreateNutritionalPlanCommand command = new CreateNutritionalPlanCommand(clientId, nutritionistId, planDetails);

        // Act
        NutritionalPlan result = handler.handle(command);

        // Assert
        ArgumentCaptor<NutritionalPlan> captor = ArgumentCaptor.forClass(NutritionalPlan.class);
        verify(nutritionalPlanRepository).update(captor.capture());

        NutritionalPlan capturedNutritionalPlan = captor.getValue();

        // Verify that the captured NutritionalPlan contains the expected data
        assertEquals(clientId, capturedNutritionalPlan.getClientId());
        assertEquals(nutritionistId, capturedNutritionalPlan.getNutritionistId());
        assertEquals(planDetails, capturedNutritionalPlan.getPlanDetails());

        // Verify the returned NutritionalPlan is the same instance
        assertEquals(capturedNutritionalPlan, result);
    }
}
