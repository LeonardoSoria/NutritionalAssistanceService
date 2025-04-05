package com.core.domain.models.nutritionalPlan;

import com.core.domain.abstracts.AggregateRoot;
import com.core.domain.models.nutritionalPlan.events.NutritionalPlanDelivered;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NutritionalPlan extends AggregateRoot {

    private final UUID id;
    private final UUID clientId;
    private final UUID nutritionistId;
    private final List<UUID> analysisResults;
    private boolean isDelivered;
    private String planDetails;

    public NutritionalPlan(UUID clientId, UUID nutritionistId, String planDetails) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.nutritionistId = nutritionistId;
        this.planDetails = planDetails;
        this.analysisResults = new ArrayList<>();
        this.isDelivered = false;
    }

    public NutritionalPlan(UUID id, UUID clientId, UUID nutritionistId, String planDetails, List<UUID> analysisResults, boolean isDelivered) {
        this.id = id;
        this.clientId = clientId;
        this.nutritionistId = nutritionistId;
        this.planDetails = planDetails;
        this.analysisResults = analysisResults;
        this.isDelivered = isDelivered;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getNutritionistId() {
        return nutritionistId;
    }

    public List<UUID> getAnalysisResults() {
        return analysisResults;
    }

    public String getPlanDetails() {
        return planDetails;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void deliverToClient() {
        if (this.planDetails.isEmpty()) {
            throw new IllegalStateException("Cannot deliver an empty plan.");
        }
        this.isDelivered = true;
        NutritionalPlanDelivered event = new NutritionalPlanDelivered(this.id);
        publishEvent(event);
    }

    public List<UUID> reviewAnalysis() {
        return new ArrayList<>(this.analysisResults);
    }

    /**
     * Adds a new analysis result to the appointment.
     *
     * @param analysisResultId the ID of the analysis result to add.
     */
    public void addAnalysisResult(UUID analysisResultId) {
        if (analysisResults.contains(analysisResultId)) {
            throw new IllegalArgumentException("Duplicate analysis result ID. This result has already been added.");
        }
        this.analysisResults.add(analysisResultId);
    }

    /**
     * Placeholder for publishing logic. Replace this with actual event bus logic in a real-world system.
     */
    private void publishEvent(NutritionalPlanDelivered event) {
        System.out.println("Publishing event: " + event);
    }

    private NutritionalPlan() {
        this.nutritionistId = null;
        this.id = null;
        this.clientId = null;
        this.analysisResults = new ArrayList<>();
        this.planDetails = null;
    }
}
