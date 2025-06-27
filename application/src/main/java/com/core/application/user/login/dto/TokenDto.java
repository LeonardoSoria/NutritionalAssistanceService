package com.core.application.user.login.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
	private String accessToken;
	private String tokenType;
	private int expiresIn;
}
