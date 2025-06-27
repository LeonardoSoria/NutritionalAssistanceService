package com.core.application.user.create;

import an.awesome.pipelinr.Command;
import com.core.application.outbox.OutboxService;
import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.user.IUserFactory;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import com.core.domain.models.user.UserFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateUserHandler implements Command.Handler<CreateUserCommand, User> {

	private final IUserFactory userFactory;
	private final IUserRepository userRepository;
	private final OutboxService outboxService;

	public CreateUserHandler(IUserRepository userRepository, OutboxService outboxService) {
		this.userFactory = new UserFactory();
		this.userRepository = userRepository;
		this.outboxService = outboxService;
	}

	@Override
	public User handle(CreateUserCommand command) {
		User user = userFactory.create(command.username, command.password,
			command.email, command.fullName, command.address, command.role);

		userRepository.upsert(user);

		for (DomainEvent event : user.getDomainEvents()) {
			outboxService.recordEvent(event.getEventType(), event);
		}

		user.clearDomainEvents();

		return user;
	}
}
