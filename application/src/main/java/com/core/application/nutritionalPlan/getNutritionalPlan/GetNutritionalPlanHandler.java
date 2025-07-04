package com.core.application.nutritionalPlan.getNutritionalPlan;

import an.awesome.pipelinr.Command;
import com.core.application.nutritionalPlan.getNutritionalPlan.dto.NutritionalPlanDto;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GetNutritionalPlanHandler implements Command.Handler<GetNutritionalPlanQuery, List<NutritionalPlanDto>> {

	private final INutritionalPlanRepository nutritionalPlanRepository;
	private final IUserRepository userRepository;

	public GetNutritionalPlanHandler(INutritionalPlanRepository nutritionalPlanRepository, IUserRepository userRepository) {
		this.nutritionalPlanRepository = nutritionalPlanRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<NutritionalPlanDto> handle(GetNutritionalPlanQuery query) {
		List<NutritionalPlan> plans = nutritionalPlanRepository.findByNutritionistId(query.nutritionistId);

		return plans.stream().map(plan -> {
			Optional<User> client = userRepository.findById(plan.getClientId());
			Optional<User> nutritionist = userRepository.findById(plan.getNutritionistId());

			return NutritionalPlanDto.builder()
				.id(plan.getId().toString())
				.clientId(plan.getClientId().toString())
				.clientName(client.map(User::getFullName).orElse(null))
				.nutritionistId(plan.getNutritionistId().toString())
				.nutritionistName(nutritionist.map(User::getFullName).orElse(null))
				.analysisResults(plan.getAnalysisResults()
					.stream()
					.map(UUID::toString)
					.collect(Collectors.toList()))
				.isDelivered(plan.isDelivered())
				.planDetails(plan.getPlanDetails())
				.build();
		}).collect(Collectors.toList());
	}
}
