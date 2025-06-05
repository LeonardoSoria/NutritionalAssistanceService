package com.core.application.user;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.user.User;
import lombok.Getter;

@Generated
@Getter
public class CreateUserCommand implements Command<User> {

    String username;
    String email;
    String fullName;

    public CreateUserCommand(String username, String email, String fullName) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
    }

}
