package com.core.domain.models.user.events;

import com.core.domain.abstracts.DomainEvent;
import com.core.domain.shared.DateValue;

import java.time.LocalDate;
import java.util.UUID;

public class UserCreated extends DomainEvent {

	private final UUID id;
	private final String username;
	private final String email;
	private final String fullName;
	private final LocalDate createdAt;

    public UserCreated(UUID id, String username, String email, String fullName, DateValue createdAt) {
        super();
        this.id = id;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
		this.createdAt = createdAt.toLocalDate();

    }

    public UUID getId() {
        return id;
    }

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	@Override
	public String getEventType() {
		return "USER_CREATED";
	}
}
