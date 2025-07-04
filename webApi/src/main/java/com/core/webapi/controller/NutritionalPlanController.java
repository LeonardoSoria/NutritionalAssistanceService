package com.core.webapi.controller;

import an.awesome.pipelinr.Pipeline;
import com.core.application.nutritionalPlan.createNutritionalPlan.CreateNutritionalPlanCommand;
import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanQuery;
import com.core.application.nutritionalPlan.getNutritionalPlan.dto.NutritionalPlanDto;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.webapi.dto.request.CreateNutritionalPlanRequest;
import com.core.webapi.dto.response.NutritionalPlanResponse;
import com.core.webapi.mapper.NutritionalPlanMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/nutritionalPlan")
public class NutritionalPlanController {

    final Pipeline pipeline;

    public NutritionalPlanController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/create")
    public ResponseEntity<NutritionalPlanResponse> createAppointment(
            @RequestBody @Valid CreateNutritionalPlanRequest createNutritionalPlanRequest) {
        CreateNutritionalPlanCommand createNutritionalPlanCommand = new CreateNutritionalPlanCommand(
                UUID.fromString(createNutritionalPlanRequest.getClientId()),
                UUID.fromString(createNutritionalPlanRequest.getNutritionistId()),
                createNutritionalPlanRequest.getPlanDetails()
        );
        NutritionalPlan createdNutritionalPlan = createNutritionalPlanCommand.execute(pipeline);
        NutritionalPlanResponse response = NutritionalPlanMapper.mapToNutritionalPlanResponse(createdNutritionalPlan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/nutritionist/{nutritionistId}")
    public ResponseEntity<List<NutritionalPlanResponse>> getAppointmentByNutritionistId(@PathVariable String nutritionistId) {
        GetNutritionalPlanQuery getNutritionalPlanQuery = new GetNutritionalPlanQuery(UUID.fromString(nutritionistId));
        List<NutritionalPlanDto> nutritionalPlans = getNutritionalPlanQuery.execute(pipeline);
        List<NutritionalPlanResponse> responseList = NutritionalPlanMapper.mapToNutritionalPlanList(nutritionalPlans);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
