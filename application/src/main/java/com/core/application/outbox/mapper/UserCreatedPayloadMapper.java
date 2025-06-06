package com.core.application.outbox.mapper;

import com.core.domain.models.appointment.events.AppointmentScheduled;
import com.core.domain.models.user.events.UserCreated;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserCreatedPayloadMapper implements OutboxPayloadMapper<UserCreated> {

	@Override
	public boolean supports(Object event) {
		return event instanceof UserCreated;
	}

	@Override
	public Map<String, Object> toPayload(UserCreated event) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", event.getId());
		map.put("username", event.getUsername());
		map.put("email", event.getEmail());
		map.put("fullName", event.getFullName());
		map.put("createdAt", event.getCreatedAt());
		return map;
	}
}
