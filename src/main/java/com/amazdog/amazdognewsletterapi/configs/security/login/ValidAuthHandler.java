package com.amazdog.amazdognewsletterapi.configs.security.login;

import com.amazdog.amazdognewsletterapi.configs.security.utils.TokenUtils;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class ValidAuthHandler implements AuthenticationSuccessHandler {

	private final TokenUtils tokenUtils;

	public ValidAuthHandler(TokenUtils tokenUtils) {
		this.tokenUtils = tokenUtils;
	}


	@Override
	public void onAuthenticationSuccess
			(HttpServletRequest request,
			 HttpServletResponse response,
			 Authentication authentication) {

		User user = (User) authentication.getPrincipal();

		String accessToken = tokenUtils.createToken(
				Instant.now().plus(1, ChronoUnit.SECONDS),
				user.getUsername(),
				user.getId(),
				user.getAuthorities());

		response.addHeader("ACCESS_TOKEN", accessToken);

	}
}
