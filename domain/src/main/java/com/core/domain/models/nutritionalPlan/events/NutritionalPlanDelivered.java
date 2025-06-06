package com.core.domain.models.nutritionalPlan.events;

import com.core.domain.abstracts.DomainEvent;

import java.util.UUID;

public class NutritionalPlanDelivered extends DomainEvent {

    private final UUID nutritionalPlanId;

    public NutritionalPlanDelivered(UUID nutritionalPlanId) {
        super();
        this.nutritionalPlanId = nutritionalPlanId;
    }

    public UUID getAppointmentId() {
        return nutritionalPlanId;
    }

	@Override
	public String getEventType() {
		return "NUTRITIONAL_PLAN_DELIVERED";
	}
}
