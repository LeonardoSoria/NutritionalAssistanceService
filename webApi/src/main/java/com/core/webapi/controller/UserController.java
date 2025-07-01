package com.core.webapi.controller;

import an.awesome.pipelinr.Pipeline;
import com.core.application.user.create.CreateUserCommand;
import com.core.application.user.login.LoginUserCommand;
import com.core.application.user.login.dto.LoginDto;
import com.core.domain.models.user.User;
import com.core.webapi.dto.request.CreateUserRequest;
import com.core.webapi.dto.request.LoginRequest;
import com.core.webapi.dto.response.LoginResponse;
import com.core.webapi.dto.response.UserResponse;
import com.core.webapi.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
			createUserRequest.getAddress(),
			createUserRequest.getRole()
		);
		User createdUser = createUserCommand.execute(pipeline);
		UserResponse response = UserMapper.mapToUserResponse(createdUser);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(
		@RequestBody @Valid LoginRequest loginRequest) {

		LoginUserCommand loginCommand = new LoginUserCommand(
			loginRequest.getEmail(),
			loginRequest.getPassword()
		);
		LoginDto loginDto = loginCommand.execute(pipeline);

		LoginResponse response = new LoginResponse(loginDto.getToken(), loginDto.getTokenType(), loginDto.getExpiresIn(),
			loginDto.getUserId(), loginDto.getUsername(), loginDto.getEmail(), loginDto.getRole());
		return ResponseEntity.ok(response);
	}
}
