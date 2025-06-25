package com.core.webapi.controller;

import an.awesome.pipelinr.Pipeline;
import com.core.application.nutritionalPlan.createNutritionalPlan.CreateNutritionalPlanCommand;
import com.core.application.nutritionalPlan.getNutritionalPlan.GetNutritionalPlanQuery;
import com.core.application.user.CreateUserCommand;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.domain.models.user.User;
import com.core.webapi.dto.request.CreateNutritionalPlanRequest;
import com.core.webapi.dto.request.CreateUserRequest;
import com.core.webapi.dto.response.NutritionalPlanResponse;
import com.core.webapi.dto.response.UserResponse;
import com.core.webapi.mapper.NutritionalPlanMapper;
import com.core.webapi.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

	final Pipeline pipeline;

	public UserController(Pipeline pipeline) {
		this.pipeline = pipeline;
	}

	@PostMapping("/create")
	public ResponseEntity<UserResponse> createUser(
		@RequestBody @Valid CreateUserRequest createUserRequest) {
		CreateUserCommand createUserCommand = new CreateUserCommand(
			createUserRequest.getUsername(),
			createUserRequest.getPassword(),
			createUserRequest.getEmail(),
			createUserRequest.getFullName(),
			createUserRequest.getAddress()
		);
		User createdUser = createUserCommand.execute(pipeline);
		UserResponse response = UserMapper.mapToUserResponse(createdUser);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
