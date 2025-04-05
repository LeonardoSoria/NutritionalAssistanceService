package com.core.infrastructure.mapper;

import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.infrastructure.entity.NutritionalPlanEntity;

public class NutritionalPlanPersistenceMapper {

    public static NutritionalPlanEntity toEntity(NutritionalPlan domain) {
        return NutritionalPlanEntity.builder()
                .id(domain.getId())
                .clientId(domain.getClientId())
                .nutritionistId(domain.getNutritionistId())
                .analysisResults(domain.getAnalysisResults())  // Assuming it's a List<UUID>
                .isDelivered(domain.isDelivered())
                .planDetails(domain.getPlanDetails())
                .build();
    }

    public static NutritionalPlan toDomainModel(NutritionalPlanEntity entity) {
        return new NutritionalPlan(
                entity.getId(),
                entity.getClientId(),
                entity.getNutritionistId(),
                entity.getPlanDetails(),
                entity.getAnalysisResults(),
                entity.isDelivered()
        );
    }
}
