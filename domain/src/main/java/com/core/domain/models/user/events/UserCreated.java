package com.core.domain.models.user.events;

import com.core.domain.abstracts.DomainEvent;
import com.core.domain.shared.DateValue;

import java.time.LocalDate;
import java.util.UUID;

public class UserCreated extends DomainEvent {

	private final UUID id;
	private final String username;
	private final String password;
	private final String email;
	private final String fullName;
	private final String address;
	private final LocalDate createdAt;

	public UserCreated(UUID id, String username, String password, String email, String fullName, String address, DateValue createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}
}
