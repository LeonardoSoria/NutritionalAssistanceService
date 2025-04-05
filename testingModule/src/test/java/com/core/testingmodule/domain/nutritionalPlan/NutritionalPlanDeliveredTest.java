package com.core.testingmodule.domain.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.events.NutritionalPlanDelivered;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalPlanDeliveredTest {

    @Test
    void constructor_ShouldInitializeNutritionalPlanId() {
        UUID nutritionalPlanId = UUID.randomUUID();

        NutritionalPlanDelivered event = new NutritionalPlanDelivered(nutritionalPlanId);

        assertNotNull(event);
        assertEquals(nutritionalPlanId, event.getAppointmentId());
    }
}
