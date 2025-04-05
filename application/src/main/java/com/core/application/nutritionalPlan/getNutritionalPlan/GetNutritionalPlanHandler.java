package com.core.application.nutritionalPlan.getNutritionalPlan;

import an.awesome.pipelinr.Command;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GetNutritionalPlanHandler implements Command.Handler<GetNutritionalPlanQuery, List<NutritionalPlan>> {

    private final INutritionalPlanRepository nutritionalPlanRepository;

    public GetNutritionalPlanHandler(INutritionalPlanRepository nutritionalPlanRepository) {
        this.nutritionalPlanRepository = nutritionalPlanRepository;
    }

    @Override
    public List<NutritionalPlan> handle(GetNutritionalPlanQuery query) {
        return nutritionalPlanRepository.findByClientId(query.clientId);
    }
}
