package com.core.testingmodule.infrastructure.nutritionalPlan;

import static org.junit.jupiter.api.Assertions.*;

import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.infrastructure.entity.NutritionalPlanEntity;
import com.core.infrastructure.mapper.NutritionalPlanPersistenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

class NutritionalPlanPersistenceMapperTest {

    private NutritionalPlanEntity nutritionalPlanEntity;
    private NutritionalPlan domainNutritionalPlan;

    private UUID nutritionalPlanId = UUID.randomUUID();
    private UUID clientId = UUID.randomUUID();
    private UUID nutritionistId = UUID.randomUUID();
    private UUID analysisResultId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        // Setup NutritionalPlanEntity for testing
        nutritionalPlanEntity = NutritionalPlanEntity.builder()
                .id(nutritionalPlanId)
                .clientId(clientId)
                .nutritionistId(nutritionistId)
                .analysisResults(Arrays.asList(analysisResultId))
                .isDelivered(true)
                .planDetails("Plan details here")
                .build();

        // Setup domain model for testing
        domainNutritionalPlan = new NutritionalPlan(
                nutritionalPlanId,
                clientId,
                nutritionistId,
                "Plan details here",
                Arrays.asList(analysisResultId),
                true
        );
    }

    @Test
    void testToEntity() {
        // Perform conversion from domain model to entity
        NutritionalPlanEntity result = NutritionalPlanPersistenceMapper.toEntity(domainNutritionalPlan);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(domainNutritionalPlan.getId(), result.getId());
        assertEquals(domainNutritionalPlan.getClientId(), result.getClientId());
        assertEquals(domainNutritionalPlan.getNutritionistId(), result.getNutritionistId());
        assertEquals(domainNutritionalPlan.getPlanDetails(), result.getPlanDetails());
        assertEquals(domainNutritionalPlan.getAnalysisResults(), result.getAnalysisResults());
        assertTrue(result.isDelivered());
    }

    @Test
    void testToDomainModel() {
        // Perform conversion from entity to domain model
        NutritionalPlan result = NutritionalPlanPersistenceMapper.toDomainModel(nutritionalPlanEntity);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(nutritionalPlanEntity.getId(), result.getId());
        assertEquals(nutritionalPlanEntity.getClientId(), result.getClientId());
        assertEquals(nutritionalPlanEntity.getNutritionistId(), result.getNutritionistId());
        assertEquals(nutritionalPlanEntity.getPlanDetails(), result.getPlanDetails());
        assertEquals(nutritionalPlanEntity.getAnalysisResults(), result.getAnalysisResults());
        assertTrue(result.isDelivered());
    }

    @Test
    void testToDomainModelWithEmptyAnalysisResults() {
        // Set the analysisResults to an empty list
        nutritionalPlanEntity.setAnalysisResults(Collections.emptyList());

        // Perform conversion from entity to domain model
        NutritionalPlan result = NutritionalPlanPersistenceMapper.toDomainModel(nutritionalPlanEntity);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(Collections.emptyList(), result.getAnalysisResults());
    }
}
