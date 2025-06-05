package com.core.domain.models.user;

import com.core.domain.abstracts.AggregateRoot;
import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.user.events.UserCreated;
import com.core.domain.shared.DateValue;

import java.time.LocalDate;
import java.util.UUID;

public class User extends AggregateRoot {

    private final UUID id;
    private final String username;
    private final String email;
    private final String fullName;
    private final DateValue createdAt;

    public User(String username, String email, String fullName) {
        this.id = UUID.randomUUID();
		this.username = username;
		this.email = email;
		this.fullName = fullName;
		this.createdAt = DateValue.from(LocalDate.now());
		publishEvent(new UserCreated(this.id, this.username, this.email, this.fullName, this.createdAt));
    }

    public User(UUID id, String username, String email, String fullName, DateValue createdAt) {
        this.id = id;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
		this.createdAt = createdAt;
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

	public DateValue getCreatedAt() {
		return createdAt;
	}

    /**
     * Placeholder for publishing logic
     */
    private void publishEvent(DomainEvent event) {
		addDomainEvent(event);
    }

    private User() {
        this.id = null;
        this.username = null;
        this.fullName = null;
        this.email = null;
        this.createdAt = null;
    }
}
