package com.core.testingmodule.application.nutritionalPlan;

import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanQuery;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetNutritionalPlanQueryTest {

    @Test
    void testConstructor_Successful_WhenValidClientId() {
        // Arrange
        UUID nutritionistId = UUID.randomUUID();

        // Act
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(nutritionistId);

        // Assert
        assertNotNull(query, "GetNutritionalPlanQuery instance should be created.");
        assertEquals(nutritionistId, query.getNutritionistId(), "The nutritionistId should be set correctly.");
    }

    @Test
    void testGetClientId_ReturnsCorrectClientId() {
        // Arrange
        UUID nutritionistId = UUID.randomUUID();
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(nutritionistId);

        // Act
        UUID result = query.getNutritionistId();

        // Assert
        assertEquals(nutritionistId, result, "The nutritionistId should be returned correctly.");
    }
}
