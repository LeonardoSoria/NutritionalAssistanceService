package com.core.infrastructure.auth;

import com.core.domain.models.auth.ITokenService;
import com.core.domain.models.auth.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AzureTokenServiceImpl implements ITokenService {

	private final RestTemplate restTemplate;

	public AzureTokenServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static final String _CLIENT_ID = "client_id";
	private static final String _CLIENT_SECRET = "client_secret";
	private static final String _SCOPE = "scope";
	private static final String _GRANT_TYPE = "grant_type";
	private static final String _ACCESS_TOKEN = "access_token";
	private static final String _TOKEN_TYPE = "token_type";
	private static final String _EXPIRES_IN = "expires_in";

	@Override
	public Token getToken() {
		String clientId = System.getenv("AZURE_CLIENT_ID");
		String clientSecret = System.getenv("AZURE_CLIENT_SECRET");
		String scope = System.getenv("AZURE_SCOPE");
		String grantType = System.getenv("AZURE_GRANT_TYPE");
		String azureUrl = System.getenv("AZURE_URL");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add(_CLIENT_ID, clientId);
		body.add(_CLIENT_SECRET, clientSecret);
		body.add(_SCOPE, scope);
		body.add(_GRANT_TYPE, grantType);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		ResponseEntity<Map> response = restTemplate.postForEntity(azureUrl, request, Map.class);
		if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
			throw new RuntimeException("Failed to retrieve access token from Azure.");
		}

		String token = (String) response.getBody().get(_ACCESS_TOKEN);
		String type = (String) response.getBody().get(_TOKEN_TYPE);
		Integer expiresIn = (Integer) response.getBody().get(_EXPIRES_IN);

		return new Token(token, type, expiresIn != null ? expiresIn : 0);
	}
}
