package com.core.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@SuperBuilder
public class OutboxMessageEntity {

	@Id
	private UUID id;

	private String eventType;
	private String eventVersion;

	@Column(name = "body", columnDefinition = "LONGTEXT", nullable = false)
	private String body;

	private LocalDateTime timestamp;

	private String source;

	private boolean processed = false;
}
