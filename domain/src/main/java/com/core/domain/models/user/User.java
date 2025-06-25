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
    private final String password;
    private final String email;
    private final String fullName;
    private final String address;
    private final DateValue createdAt;

    public User(String username, String password, String email, String fullName, String address) {
        this.id = UUID.randomUUID();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.createdAt = DateValue.from(LocalDate.now());
		publishEvent(new UserCreated(this.id, this.username, this.password, this.email, this.fullName, this.address, this.createdAt));
    }

    public User(UUID id, String username, String password, String email, String fullName, String address, DateValue createdAt) {
        this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.createdAt = createdAt;
    }

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
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
        this.password = null;
        this.fullName = null;
        this.email = null;
        this.address = null;
        this.createdAt = null;
    }
}
