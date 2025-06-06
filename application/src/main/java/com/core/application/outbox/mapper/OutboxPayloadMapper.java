package com.core.application.outbox.mapper;

import java.util.Map;

public interface OutboxPayloadMapper<T> {
	boolean supports(Object event);
	Map<String, Object> toPayload(T event);
}
