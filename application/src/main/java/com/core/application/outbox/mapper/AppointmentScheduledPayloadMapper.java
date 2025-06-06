package com.core.application.outbox.mapper;

import com.core.domain.models.appointment.events.AppointmentScheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppointmentScheduledPayloadMapper implements OutboxPayloadMapper<AppointmentScheduled> {

	@Override
	public boolean supports(Object event) {
		return event instanceof AppointmentScheduled;
	}

	@Override
	public Map<String, Object> toPayload(AppointmentScheduled event) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", event.getId());
		map.put("clientId", event.getClientId());
		map.put("date", event.getDate());
		map.put("status", event.getStatus());
		map.put("analysisRequests", event.getAnalysisRequests());
		return map;
	}
}
