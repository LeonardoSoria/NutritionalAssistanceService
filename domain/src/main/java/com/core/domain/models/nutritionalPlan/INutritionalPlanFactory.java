package com.core.domain.models.nutritionalPlan;

import java.util.UUID;

public interface INutritionalPlanFactory {
    NutritionalPlan create(UUID clientId, UUID nutritionistId, String planDetails);
}