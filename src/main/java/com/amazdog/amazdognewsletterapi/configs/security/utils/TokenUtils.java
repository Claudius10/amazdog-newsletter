package com.amazdog.amazdognewsletterapi.configs.security.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class TokenUtils {

	private final JWTUtils jwtUtils;

	public TokenUtils(JWTUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	public String createToken(Instant expiry, String username, Long userId, Collection<? extends GrantedAuthority> authorities) {
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("issuer to be set when known")
				.issuedAt(Instant.now())
				.expiresAt(expiry)
				.subject(username)
				.claim("id", userId)
				.claim("roles", parseAuthorities(authorities))
				.build();
		return jwtUtils.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public String parseAuthorities(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
	}
}
