package com.core.domain.models.auth;

public class Token {

	private final String token;
	private final String type;
	private final Integer expiresIn;


	public Token(String token, String type, Integer expiresIn) {
		this.token = token;
		this.type = type;
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}
}
