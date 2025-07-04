package com.core.testingmodule.domain.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.events.NutritionalPlanCreated;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class NutritionalPlanCreatedTest {

    @Test
    void testNutritionalPlanCreatedEvent() {
        UUID planId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "1000 kcal per day";

        NutritionalPlanCreated event = new NutritionalPlanCreated(planId, clientId, nutritionistId, planDetails);

        assertThat(event.getId()).isEqualTo(planId);
        assertThat(event.getClientId()).isEqualTo(clientId);
        assertThat(event.getNutritionistId()).isEqualTo(nutritionistId);
        assertThat(event.getPlanDetails()).isEqualTo(planDetails);
        assertThat(event.isDelivered()).isFalse();
        assertThat(event.getAnalysisResults()).isNotNull().isEmpty();
        assertThat(event.getEventType()).isEqualTo("NUTRITIONAL_PLAN_CREATED");
    }
}
