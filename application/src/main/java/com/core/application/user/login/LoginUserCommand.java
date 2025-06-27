package com.core.application.user.login;

import an.awesome.pipelinr.Command;
import com.core.application.user.login.dto.LoginDto;
import com.core.domain.annotations.Generated;
import com.core.domain.models.user.User;
import lombok.Getter;

@Generated
@Getter
public class LoginUserCommand implements Command<LoginDto> {

    String email;
	String password;

    public LoginUserCommand(String email, String password) {
        this.email = email;
		this.password = password;
    }

}
