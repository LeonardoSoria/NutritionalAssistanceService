package com.core.testingmodule.application.outbox;

import com.core.application.outbox.mapper.NutritionalPlanCreatedPayloadMapper;
import com.core.domain.models.nutritionalPlan.events.NutritionalPlanCreated;
import com.core.domain.models.appointment.AnalysisResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalPlanCreatedPayloadMapperTest {

    private NutritionalPlanCreatedPayloadMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new NutritionalPlanCreatedPayloadMapper();
    }

    @Test
    void supports_returnsTrueForNutritionalPlanCreated() {
        NutritionalPlanCreated event = new NutritionalPlanCreated(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            "some plan details"
        );
        assertTrue(mapper.supports(event));
    }

    @Test
    void supports_returnsFalseForOtherTypes() {
        String notAnEvent = "Not an event";
        assertFalse(mapper.supports(notAnEvent));
        assertFalse(mapper.supports(null));
    }

    @Test
    void toPayload_mapsFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        String planDetails = "Healthy plan";

        NutritionalPlanCreated event = new NutritionalPlanCreated(id, clientId, nutritionistId, planDetails);

        Map<String, Object> payload = mapper.toPayload(event);

        assertEquals(id, payload.get("id"));
        assertEquals(clientId, payload.get("clientId"));
        assertEquals(nutritionistId, payload.get("nutritionistId"));
        assertEquals(false, payload.get("isDelivered"));
        assertNotNull(payload.get("analysisResultIds"));
        assertTrue(payload.get("analysisResultIds") instanceof List);
        assertEquals(planDetails, payload.get("planDetails"));
    }
}
