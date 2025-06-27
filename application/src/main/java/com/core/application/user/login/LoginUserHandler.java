package com.core.application.user.login;

import an.awesome.pipelinr.Command;
import com.core.application.user.login.dto.LoginDto;
import com.core.domain.exceptions.InvalidCredentialsException;
import com.core.domain.models.auth.ITokenService;
import com.core.domain.models.auth.Token;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import org.springframework.stereotype.Component;

@Component
public class LoginUserHandler implements Command.Handler<LoginUserCommand, LoginDto> {

	private final IUserRepository userRepository;
	private final ITokenService tokenService;

	public LoginUserHandler(IUserRepository userRepository, ITokenService tokenService) {
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	@Override
	public LoginDto handle(LoginUserCommand command) {
		User userLogged = userRepository.login(command.getEmail(), command.getPassword());
		if (userLogged == null) {
			throw new InvalidCredentialsException("Invalid email or password.");
		}

		Token token = tokenService.getToken();

		LoginDto loginDto = new LoginDto();
		loginDto.setToken(token.getToken());
		loginDto.setTokenType(token.getType());
		loginDto.setExpiresIn(token.getExpiresIn());
		loginDto.setUserId(userLogged.getId());
		loginDto.setUsername(userLogged.getUsername());
		loginDto.setEmail(userLogged.getEmail());

		return loginDto;
	}
}
