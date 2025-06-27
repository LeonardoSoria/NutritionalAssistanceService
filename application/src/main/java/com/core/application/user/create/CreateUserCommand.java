package com.core.application.user.create;

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
    String role;

    public CreateUserCommand(String username, String password, String email, String fullName, String address, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.role = role;
    }

}
