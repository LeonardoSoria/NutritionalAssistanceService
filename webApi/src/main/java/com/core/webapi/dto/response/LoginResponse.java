package com.core.webapi.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String token;
	private String tokenType;
	private Integer expiresIn;
	private UUID userId;
	private String username;
	private String email;
}
