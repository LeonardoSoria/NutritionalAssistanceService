package com.core.application.user.getUser;

import an.awesome.pipelinr.Command;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUsersHandler implements Command.Handler<GetUsersQuery, List<User>> {

    private final IUserRepository userRepository;

    public GetUsersHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetUsersQuery query) {
        return userRepository.findUsers();
    }
}
