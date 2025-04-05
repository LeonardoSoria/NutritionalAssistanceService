package com.core.testingmodule.infrastructure.nutritionalPlan;

import static org.junit.jupiter.api.Assertions.*;

import com.core.infrastructure.entity.NutritionalPlanEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class NutritionalPlanEntityTest {

    private NutritionalPlanEntity nutritionalPlanEntity;
    private UUID id;
    private UUID clientId;
    private UUID nutritionistId;
    private List<UUID> analysisResults;
    private boolean isDelivered;
    private String planDetails;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        clientId = UUID.randomUUID();
        nutritionistId = UUID.randomUUID();
        analysisResults = new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID()));
        isDelivered = true;
        planDetails = "Detailed plan for the client";

        nutritionalPlanEntity = new NutritionalPlanEntity();
        nutritionalPlanEntity.setId(id);
        nutritionalPlanEntity.setClientId(clientId);
        nutritionalPlanEntity.setNutritionistId(nutritionistId);
        nutritionalPlanEntity.setAnalysisResults(analysisResults);
        nutritionalPlanEntity.setDelivered(isDelivered);
        nutritionalPlanEntity.setPlanDetails(planDetails);
    }

    @Test
    void testNoArgsConstructor() {
        NutritionalPlanEntity entity = new NutritionalPlanEntity();
        assertNotNull(entity);
    }

    @Test
    void testAllArgsConstructor() {
        NutritionalPlanEntity entity = new NutritionalPlanEntity(id, clientId, nutritionistId, analysisResults, isDelivered, planDetails);
        assertEquals(id, entity.getId());
        assertEquals(clientId, entity.getClientId());
        assertEquals(nutritionistId, entity.getNutritionistId());
        assertEquals(analysisResults, entity.getAnalysisResults());
        assertEquals(isDelivered, entity.isDelivered());
        assertEquals(planDetails, entity.getPlanDetails());
    }

    @Test
    void testSettersAndGetters() {
        UUID newId = UUID.randomUUID();
        UUID newClientId = UUID.randomUUID();
        UUID newNutritionistId = UUID.randomUUID();
        List<UUID> newAnalysisResults = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        boolean newIsDelivered = false;
        String newPlanDetails = "Updated plan details";

        nutritionalPlanEntity.setId(newId);
        nutritionalPlanEntity.setClientId(newClientId);
        nutritionalPlanEntity.setNutritionistId(newNutritionistId);
        nutritionalPlanEntity.setAnalysisResults(newAnalysisResults);
        nutritionalPlanEntity.setDelivered(newIsDelivered);
        nutritionalPlanEntity.setPlanDetails(newPlanDetails);

        assertEquals(newId, nutritionalPlanEntity.getId());
        assertEquals(newClientId, nutritionalPlanEntity.getClientId());
        assertEquals(newNutritionistId, nutritionalPlanEntity.getNutritionistId());
        assertEquals(newAnalysisResults, nutritionalPlanEntity.getAnalysisResults());
        assertEquals(newIsDelivered, nutritionalPlanEntity.isDelivered());
        assertEquals(newPlanDetails, nutritionalPlanEntity.getPlanDetails());
    }

    @Test
    void testNullAnalysisResults() {
        nutritionalPlanEntity.setAnalysisResults(null);
        assertNull(nutritionalPlanEntity.getAnalysisResults());
    }

    @Test
    void testEmptyAnalysisResults() {
        nutritionalPlanEntity.setAnalysisResults(new ArrayList<>());
        assertTrue(nutritionalPlanEntity.getAnalysisResults().isEmpty());
    }

    @Test
    void testSizeOfAnalysisResults() {
        List<UUID> retrievedList = nutritionalPlanEntity.getAnalysisResults();
        retrievedList.add(UUID.randomUUID());
        assertEquals(retrievedList.size(), nutritionalPlanEntity.getAnalysisResults().size(), "List should be have the same size!");
    }

    @Test
    void testEqualsAndHashCode() {
        NutritionalPlanEntity sameEntity = NutritionalPlanEntity.builder()
                .id(id)
                .clientId(clientId)
                .nutritionistId(nutritionistId)
                .analysisResults(analysisResults)
                .isDelivered(isDelivered)
                .planDetails(planDetails)
                .build();

        NutritionalPlanEntity differentEntity = NutritionalPlanEntity.builder()
                .id(UUID.randomUUID())
                .clientId(UUID.randomUUID())
                .nutritionistId(UUID.randomUUID())
                .analysisResults(Collections.singletonList(UUID.randomUUID()))
                .isDelivered(false)
                .planDetails("Different plan details")
                .build();

        assertEquals(nutritionalPlanEntity, sameEntity);
        assertNotEquals(nutritionalPlanEntity, differentEntity);
        assertEquals(nutritionalPlanEntity.hashCode(), sameEntity.hashCode());
        assertNotEquals(nutritionalPlanEntity.hashCode(), differentEntity.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(nutritionalPlanEntity.toString());
    }
}
