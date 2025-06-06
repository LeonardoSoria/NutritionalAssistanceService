package com.core.domain.models.nutritionalPlan.events;

import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.appointment.AnalysisResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NutritionalPlanCreated extends DomainEvent {

    private final UUID id;
    private final UUID clientId;
    private final UUID nutritionistId;
	private final boolean isDelivered;
	private final List<AnalysisResult> analysisResults;
	private final String planDetails;

    public NutritionalPlanCreated(UUID nutritionalPlanId, UUID clientId, UUID nutritionistId, String planDetails) {
        super();
        this.id = nutritionalPlanId;
		this.clientId = clientId;
		this.nutritionistId = nutritionistId;
		this.planDetails = planDetails;
		this.isDelivered = false;
		this.analysisResults = new ArrayList<>();
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

	public boolean isDelivered() {
		return isDelivered;
	}

	public List<AnalysisResult> getAnalysisResults() {
		return analysisResults;
	}

	public String getPlanDetails() {
		return planDetails;
	}

	@Override
	public String getEventType() {
		return "NUTRITIONAL_PLAN_CREATED";
	}
}
