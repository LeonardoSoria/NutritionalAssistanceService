package com.core.infrastructure.repository.nutritionalPlan;

import com.core.infrastructure.entity.NutritionalPlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NutritionalPlanCrudRepository extends CrudRepository<NutritionalPlanEntity, UUID> {
    List<NutritionalPlanEntity> findByNutritionistId(UUID nutritionistId);
}
