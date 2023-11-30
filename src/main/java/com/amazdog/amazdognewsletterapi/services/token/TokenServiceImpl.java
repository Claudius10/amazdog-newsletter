package com.amazdog.amazdognewsletterapi.services.token;

import com.amazdog.amazdognewsletterapi.entities.Token;
import com.amazdog.amazdognewsletterapi.repos.token.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	private final TokenRepository tokenRepository;

	public TokenServiceImpl(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void saveToken(Token token) {
		tokenRepository.save(token);
	}

	@Override
	public boolean tokenExists(UUID tokenId) {
		return tokenRepository.existsById(tokenId);
	}

	@Override
	public void deleteToken(UUID tokenId) {
		tokenRepository.deleteById(tokenId);
	}
}
