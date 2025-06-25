package com.core.application.user;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.user.User;
import lombok.Getter;

@Generated
@Getter
public class CreateUserCommand implements Command<User> {

    String username;
    String password;
    String email;
    String fullName;
    String address;

    public CreateUserCommand(String username, String password, String email, String fullName, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
    }

}
