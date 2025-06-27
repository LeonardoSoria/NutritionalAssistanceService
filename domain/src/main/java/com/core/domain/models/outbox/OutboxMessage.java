package com.core.domain.models.outbox;

import java.time.LocalDateTime;
import java.util.UUID;

public class OutboxMessage {

	private final UUID id;
	private final String eventType;
	private final String eventVersion;
	private final String body;
	private final LocalDateTime timestamp;
	private final String source;
	private boolean processed;

	private final static String _SERVICE_NAME = "nutritional-service";
	private final static String _EVENT_VERSION = "1.0";

	public OutboxMessage(String eventType, String body, LocalDateTime timestamp) {
		this.id = UUID.randomUUID();
		this.eventType = eventType;
		this.body = body;
		this.timestamp = timestamp;
		this.source = _SERVICE_NAME;
		this.eventVersion = _EVENT_VERSION;
		this.processed = false;
	}

	public OutboxMessage(UUID id, String eventType, String eventVersion, String body,
						 LocalDateTime timestamp, String source, boolean processed) {
		this.id = id;
		this.eventType = eventType;
		this.eventVersion = eventVersion;
		this.body = body;
		this.timestamp = timestamp;
		this.source = source;
		this.processed = processed;
	}

	public void process() {
		if (this.processed) {
			throw new IllegalStateException("This outbox message has already been processed");
		}
		this.processed = true;
	}

	public UUID getId() {
		return id;
	}

	public String getEventType() {
		return eventType;
	}

	public String getBody() {
		return body;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getEventVersion() {
		return eventVersion;
	}

	public String getSource() {
		return source;
	}

	public boolean isProcessed() {
		return processed;
	}
}
