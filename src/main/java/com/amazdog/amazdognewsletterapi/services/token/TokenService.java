package com.amazdog.amazdognewsletterapi.services.token;

import com.amazdog.amazdognewsletterapi.entities.Token;

import java.util.UUID;

public interface TokenService {

	void saveToken(Token token);

	boolean tokenExists(UUID tokenId);

	void deleteToken(UUID tokenId);
}
