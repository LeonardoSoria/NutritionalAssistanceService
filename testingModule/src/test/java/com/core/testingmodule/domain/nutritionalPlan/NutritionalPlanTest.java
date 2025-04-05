package com.core.testingmodule.domain.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class NutritionalPlanTest {

    private UUID clientId;
    private UUID nutritionistId;
    private String planDetails;
    private NutritionalPlan nutritionalPlan;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        nutritionistId = UUID.randomUUID();
        planDetails = "Sample plan details";
        nutritionalPlan = new NutritionalPlan(clientId, nutritionistId, planDetails);
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        // Act
        NutritionalPlan newPlan = new NutritionalPlan(clientId, nutritionistId, planDetails);

        // Assert
        assertThat(newPlan.getId()).isNotNull();
        assertThat(newPlan.getClientId()).isEqualTo(clientId);
        assertThat(newPlan.getNutritionistId()).isEqualTo(nutritionistId);
        assertThat(newPlan.getPlanDetails()).isEqualTo(planDetails);
        assertThat(newPlan.isDelivered()).isFalse();
        assertThat(newPlan.getAnalysisResults()).isEmpty();
    }

    @Test
    void deliverToClient_ShouldSetIsDeliveredToTrue_WhenPlanDetailsIsNotEmpty() {
        // Act
        nutritionalPlan.deliverToClient();

        // Assert
        assertThat(nutritionalPlan.isDelivered()).isTrue();
    }

    @Test
    void deliverToClient_ShouldThrowException_WhenPlanDetailsIsEmpty() {
        // Arrange
        NutritionalPlan emptyPlan = new NutritionalPlan(clientId, nutritionistId, "");

        // Act & Assert
        assertThatThrownBy(emptyPlan::deliverToClient)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot deliver an empty plan.");
    }

    @Test
    void addAnalysisResult_ShouldAddNewAnalysisResult() {
        // Arrange
        UUID analysisResultId = UUID.randomUUID();

        // Act
        nutritionalPlan.addAnalysisResult(analysisResultId);

        // Assert
        assertThat(nutritionalPlan.getAnalysisResults()).containsExactly(analysisResultId);
    }

    @Test
    void addAnalysisResult_ShouldThrowException_WhenAddingDuplicateAnalysisResult() {
        // Arrange
        UUID analysisResultId = UUID.randomUUID();
        nutritionalPlan.addAnalysisResult(analysisResultId);

        // Act & Assert
        assertThatThrownBy(() -> nutritionalPlan.addAnalysisResult(analysisResultId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Duplicate analysis result ID. This result has already been added.");
    }

    @Test
    void reviewAnalysis_ShouldReturnACopyOfAnalysisResults() {
        // Arrange
        UUID analysisResultId = UUID.randomUUID();
        nutritionalPlan.addAnalysisResult(analysisResultId);

        // Act
        List<UUID> analysisResults = nutritionalPlan.reviewAnalysis();

        // Assert
        assertThat(analysisResults).containsExactly(analysisResultId);
        assertThat(analysisResults).isNotSameAs(nutritionalPlan.getAnalysisResults());
    }
}
