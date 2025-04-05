package com.core.domain.models.nutritionalPlan;

import java.util.UUID;

public class NutritionalPlanFactory implements INutritionalPlanFactory {

    /**
     * Creates a new NutritionalPlan with the given client ID and nutritionist ID.
     *
     * @param clientId       The ID of the client.
     * @param nutritionistId The ID of the nutritionist.
     * @return A new NutritionalPlan instance.
     * @throws IllegalArgumentException if clientId or nutritionistId is null or invalid.
     */
    @Override
    public NutritionalPlan create(UUID clientId, UUID nutritionistId, String planDetails) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID is required and cannot be null.");
        }
        if (nutritionistId == null) {
            throw new IllegalArgumentException("Nutritionist ID is required and cannot be null.");
        }
        return new NutritionalPlan(clientId, nutritionistId, planDetails);
    }
}
