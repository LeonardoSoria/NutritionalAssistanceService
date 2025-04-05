package com.core.testingmodule.domain.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.domain.models.nutritionalPlan.NutritionalPlanFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalPlanFactoryTest {

    private NutritionalPlanFactory factory;

    @BeforeEach
    void setUp() {
        factory = new NutritionalPlanFactory();
    }

    @Test
    void create_ShouldReturnNutritionalPlan_WhenValidInputsAreProvided() {
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "Sample meal plan details";

        NutritionalPlan plan = factory.create(clientId, nutritionistId, planDetails);

        assertNotNull(plan);
        assertEquals(clientId, plan.getClientId());
        assertEquals(nutritionistId, plan.getNutritionistId());
        assertEquals(planDetails, plan.getPlanDetails());
    }

    @Test
    void create_ShouldThrowException_WhenClientIdIsNull() {
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "Sample meal plan details";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> factory.create(null, nutritionistId, planDetails));

        assertEquals("Client ID is required and cannot be null.", exception.getMessage());
    }

    @Test
    void create_ShouldThrowException_WhenNutritionistIdIsNull() {
        UUID clientId = UUID.randomUUID();
        String planDetails = "Sample meal plan details";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> factory.create(clientId, null, planDetails));

        assertEquals("Nutritionist ID is required and cannot be null.", exception.getMessage());
    }
}
