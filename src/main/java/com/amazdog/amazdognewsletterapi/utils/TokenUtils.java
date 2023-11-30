package com.amazdog.amazdognewsletterapi.utils;

import com.amazdog.amazdognewsletterapi.entities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class TokenUtils {

	private final ObjectMapper objectMapper;

	public TokenUtils(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		objectMapper.registerModule(new JavaTimeModule());
	}

	public String serializeToken(Token token) {
		try {
			return objectMapper.writeValueAsString(token);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public Token deserializeToken(String jsonToken) {
		try {
			return objectMapper.readValue(jsonToken, Token.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public String encodeToken(Token token) {
		return Base64.getEncoder().encodeToString(serializeToken(token).getBytes());
	}

	public Token decodeToken(String encodedSerializedToken) {
		return deserializeToken(new String(Base64.getDecoder().decode(encodedSerializedToken)));
	}
}
