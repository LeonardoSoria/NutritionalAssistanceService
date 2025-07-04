package com.core.domain.models.nutritionalPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INutritionalPlanRepository {

    /**
     * Finds a NutritionalPlan by its ID.
     *
     * @param id The UUID of the NutritionalPlan.
     * @return An Optional containing the NutritionalPlan if found, otherwise empty.
     */
    Optional<NutritionalPlan> findById(UUID id);

    /**
     * Finds a NutritionalPlan by a clientId.
     *
     * @param nutritionistId The UUID of the nutritionist.
     * @return A List of NutritionalPlan if found, otherwise empty.
     */
    List<NutritionalPlan> findByNutritionistId(UUID nutritionistId);

    /**
     * Updates an existing NutritionalPlan.
     *
     * @param nutritionalPlan The NutritionalPlan to update.
     */
    NutritionalPlan update(NutritionalPlan nutritionalPlan);

    /**
     * Deletes a NutritionalPlan by its ID.
     *
     * @param id The UUID of the NutritionalPlan to delete.
     */
    void deleteById(UUID id);
}
