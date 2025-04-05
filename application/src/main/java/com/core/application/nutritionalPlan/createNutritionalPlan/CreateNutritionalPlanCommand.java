package com.core.application.nutritionalPlan.createNutritionalPlan;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import lombok.Getter;

import java.util.UUID;

@Generated
@Getter
public class CreateNutritionalPlanCommand implements Command<NutritionalPlan> {

    UUID clientId;
    UUID nutritionistId;
    String planDetails;

    public CreateNutritionalPlanCommand(UUID clientId, UUID nutritionistId, String planDetails) {
        this.clientId = clientId;
        this.nutritionistId = nutritionistId;
        this.planDetails = planDetails;
    }

}
