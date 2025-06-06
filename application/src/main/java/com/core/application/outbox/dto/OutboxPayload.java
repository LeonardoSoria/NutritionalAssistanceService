package com.core.application.outbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Map;

public class OutboxPayload {

	private final String eventType;
	private final String eventVersion;
	private final LocalDateTime timestamp;
	private final Map<String, Object> body;
	private final String source;

	public OutboxPayload(String eventType, String eventVersion, LocalDateTime timestamp, Map<String, Object> body, String source) {
		this.eventType = eventType;
		this.eventVersion = eventVersion;
		this.timestamp = timestamp;
		this.body = body;
		this.source = source;
	}

	@JsonProperty("eventType")
	public String getEventType() {
		return eventType;
	}

	@JsonProperty("eventVersion")
	public String getEventVersion() {
		return eventVersion;
	}

	@JsonProperty("timestamp")
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@JsonProperty("body")
	public Map<String, Object> getBody() {
		return body;
	}

	@JsonProperty("source")
	public String getSource() {
		return source;
	}
}
