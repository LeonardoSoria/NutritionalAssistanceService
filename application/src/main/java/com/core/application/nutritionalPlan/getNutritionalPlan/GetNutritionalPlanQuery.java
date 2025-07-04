package com.core.application.nutritionalPlan.getNutritionalPlan;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Generated
@Getter
public class GetNutritionalPlanQuery implements Command<List<NutritionalPlan>> {

    UUID nutritionistId;

    public GetNutritionalPlanQuery(UUID nutritionistId) {
        this.nutritionistId = nutritionistId;
    }
}
