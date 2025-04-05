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
        UUID clientId = UUID.randomUUID();

        // Act
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(clientId);

        // Assert
        assertNotNull(query, "GetNutritionalPlanQuery instance should be created.");
        assertEquals(clientId, query.getClientId(), "The clientId should be set correctly.");
    }

    @Test
    void testGetClientId_ReturnsCorrectClientId() {
        // Arrange
        UUID clientId = UUID.randomUUID();
        GetNutritionalPlanQuery query = new GetNutritionalPlanQuery(clientId);

        // Act
        UUID result = query.getClientId();

        // Assert
        assertEquals(clientId, result, "The clientId should be returned correctly.");
    }
}
