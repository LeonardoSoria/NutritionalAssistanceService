package com.core.testingmodule.infrastructure.outbox;

import com.core.infrastructure.entity.OutboxMessageEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OutboxMessageEntityTest {

	@Test
	void testNoArgsConstructorAndSetters() {
		OutboxMessageEntity entity = new OutboxMessageEntity();

		UUID id = UUID.randomUUID();
		String eventType = "TestEvent";
		String eventVersion = "1.0";
		String body = "{\"key\":\"value\"}";
		LocalDateTime timestamp = LocalDateTime.now();
		String source = "unit-test";
		boolean processed = true;

		entity.setId(id);
		entity.setEventType(eventType);
		entity.setEventVersion(eventVersion);
		entity.setBody(body);
		entity.setTimestamp(timestamp);
		entity.setSource(source);
		entity.setProcessed(processed);

		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getEventType()).isEqualTo(eventType);
		assertThat(entity.getEventVersion()).isEqualTo(eventVersion);
		assertThat(entity.getBody()).isEqualTo(body);
		assertThat(entity.getTimestamp()).isEqualTo(timestamp);
		assertThat(entity.getSource()).isEqualTo(source);
		assertThat(entity.isProcessed()).isEqualTo(processed);
	}

	@Test
	void testAllArgsConstructor() {
		UUID id = UUID.randomUUID();
		String eventType = "EventType";
		String eventVersion = "v2";
		String body = "body content";
		LocalDateTime timestamp = LocalDateTime.of(2025, 7, 4, 12, 30);
		String source = "source-name";
		boolean processed = false;

		OutboxMessageEntity entity = new OutboxMessageEntity(
			id,
			eventType,
			eventVersion,
			body,
			timestamp,
			source,
			processed
		);

		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getEventType()).isEqualTo(eventType);
		assertThat(entity.getEventVersion()).isEqualTo(eventVersion);
		assertThat(entity.getBody()).isEqualTo(body);
		assertThat(entity.getTimestamp()).isEqualTo(timestamp);
		assertThat(entity.getSource()).isEqualTo(source);
		assertThat(entity.isProcessed()).isEqualTo(processed);
	}

	@Test
	void testBuilder() {
		UUID id = UUID.randomUUID();
		String eventType = "BuilderEvent";
		String eventVersion = "1.1";
		String body = "builder body";
		LocalDateTime timestamp = LocalDateTime.now();
		String source = "builder-source";
		boolean processed = true;

		OutboxMessageEntity entity = OutboxMessageEntity.builder()
			.id(id)
			.eventType(eventType)
			.eventVersion(eventVersion)
			.body(body)
			.timestamp(timestamp)
			.source(source)
			.processed(processed)
			.build();

		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getEventType()).isEqualTo(eventType);
		assertThat(entity.getEventVersion()).isEqualTo(eventVersion);
		assertThat(entity.getBody()).isEqualTo(body);
		assertThat(entity.getTimestamp()).isEqualTo(timestamp);
		assertThat(entity.getSource()).isEqualTo(source);
		assertThat(entity.isProcessed()).isEqualTo(processed);
	}

	@Test
	void testDefaultProcessedValue() {
		// processed defaults to false if not set
		OutboxMessageEntity entity = new OutboxMessageEntity();
		assertThat(entity.isProcessed()).isFalse();
	}

	@Test
	void testEqualsAndHashCode() {
		UUID id = UUID.randomUUID();
		OutboxMessageEntity entity1 = OutboxMessageEntity.builder()
			.id(id)
			.eventType("type1")
			.eventVersion("v1")
			.body("body1")
			.timestamp(LocalDateTime.now())
			.source("source1")
			.processed(true)
			.build();

		OutboxMessageEntity entity2 = OutboxMessageEntity.builder()
			.id(id)
			.eventType("type1")
			.eventVersion("v1")
			.body("body1")
			.timestamp(entity1.getTimestamp())
			.source("source1")
			.processed(true)
			.build();

		OutboxMessageEntity entity3 = OutboxMessageEntity.builder()
			.id(UUID.randomUUID())
			.eventType("type2")
			.eventVersion("v2")
			.body("body2")
			.timestamp(LocalDateTime.now())
			.source("source2")
			.processed(false)
			.build();

		// Reflexive
		assertThat(entity1).isEqualTo(entity1);

		// Symmetric
		assertThat(entity1).isEqualTo(entity2);
		assertThat(entity2).isEqualTo(entity1);

		// Transitive
		OutboxMessageEntity entity2Copy = OutboxMessageEntity.builder()
			.id(id)
			.eventType("type1")
			.eventVersion("v1")
			.body("body1")
			.timestamp(entity1.getTimestamp())
			.source("source1")
			.processed(true)
			.build();
		assertThat(entity1).isEqualTo(entity2);
		assertThat(entity2).isEqualTo(entity2Copy);
		assertThat(entity1).isEqualTo(entity2Copy);

		// Not equals with different object
		assertThat(entity1).isNotEqualTo(entity3);

		// Not equals with null
		assertThat(entity1).isNotEqualTo(null);

		// Not equals with other class
		assertThat(entity1).isNotEqualTo("string");

		// hashCode consistency
		assertThat(entity1.hashCode()).isEqualTo(entity2.hashCode());
	}

	@Test
	void testToString() {
		UUID id = UUID.randomUUID();
		OutboxMessageEntity entity = OutboxMessageEntity.builder()
			.id(id)
			.eventType("typeToString")
			.eventVersion("vToString")
			.body("bodyToString")
			.timestamp(LocalDateTime.of(2025, 7, 4, 10, 0))
			.source("sourceToString")
			.processed(false)
			.build();

		String toStringResult = entity.toString();

		assertThat(toStringResult).contains("OutboxMessageEntity");
		assertThat(toStringResult).contains(id.toString());
		assertThat(toStringResult).contains("typeToString");
		assertThat(toStringResult).contains("vToString");
		assertThat(toStringResult).contains("bodyToString");
		assertThat(toStringResult).contains("sourceToString");
		assertThat(toStringResult).contains("processed=false");
	}
}
