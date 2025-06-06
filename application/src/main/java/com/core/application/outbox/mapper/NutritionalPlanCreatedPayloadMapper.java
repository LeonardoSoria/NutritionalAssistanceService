package com.core.application.outbox.mapper;

import com.core.domain.models.nutritionalPlan.events.NutritionalPlanCreated;
import com.core.domain.models.user.events.UserCreated;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NutritionalPlanCreatedPayloadMapper implements OutboxPayloadMapper<NutritionalPlanCreated> {

	@Override
	public boolean supports(Object event) {
		return event instanceof NutritionalPlanCreated;
	}

	@Override
	public Map<String, Object> toPayload(NutritionalPlanCreated event) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", event.getId());
		map.put("clientId", event.getClientId());
		map.put("nutritionistId", event.getNutritionistId());
		map.put("isDelivered", event.isDelivered());
		map.put("analysisResultIds", event.getAnalysisResults());
		map.put("planDetails", event.getPlanDetails());
		return map;
	}
}
