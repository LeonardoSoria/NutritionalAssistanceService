package com.core.application.user.login.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	private String token;
	private String tokenType;
	private Integer expiresIn;
	private UUID userId;
	private String username;
	private String email;
	private String role;
}
