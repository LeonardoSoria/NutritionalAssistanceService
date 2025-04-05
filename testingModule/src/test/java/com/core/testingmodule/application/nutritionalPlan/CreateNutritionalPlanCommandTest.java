package com.core.testingmodule.application.nutritionalPlan;

import com.core.application.nutritionalPlan.createNutritionalPlan.CreateNutritionalPlanCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateNutritionalPlanCommandTest {

    @Test
    void testCommandConstructor() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "Test Plan Details";

        // Act
        CreateNutritionalPlanCommand command = new CreateNutritionalPlanCommand(clientId, nutritionistId, planDetails);

        // Assert
        assertEquals(clientId, command.getClientId(), "The clientId should be correctly set in the command.");
        assertEquals(nutritionistId, command.getNutritionistId(), "The nutritionistId should be correctly set in the command.");
        assertEquals(planDetails, command.getPlanDetails(), "The planDetails should be correctly set in the command.");
    }
}
