package com.core.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class UserEntity {

	@Id
	private UUID id;

	private String username;
	private String password;
	private String email;
	private String fullName;
	private String address;
	private String role;
	private String createdAt;
}
