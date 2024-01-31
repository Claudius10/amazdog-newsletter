package com.amazdog.amazdognewsletterapi.configs.security.login;

import com.amazdog.amazdognewsletterapi.configs.security.utils.SecurityTokenUtils;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonPrimitive;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class ValidAuthHandler implements AuthenticationSuccessHandler {

	private final SecurityTokenUtils tokenUtils;

	public ValidAuthHandler(SecurityTokenUtils tokenUtils) {
		this.tokenUtils = tokenUtils;
	}

	@Override
	public void onAuthenticationSuccess
			(HttpServletRequest request,
			 HttpServletResponse response,
			 Authentication authentication) throws IOException {

		User user = (User) authentication.getPrincipal();

		Instant expiry = Instant.now().plus(1, ChronoUnit.DAYS);
		String accessToken = tokenUtils.createToken(
				expiry,
				user.getUsername(),
				user.getId(),
				user.getAuthorities());

		String userRole = user.getAuthorities().stream().findFirst().get().getAuthority();

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("ACCESS_TOKEN", new JsonPrimitive(accessToken));
		jsonObject.add("ACCESS_EXP", new JsonPrimitive(expiry.plus(1, ChronoUnit.MINUTES).toString()));
		jsonObject.add("USER_EMAIL", new JsonPrimitive(user.getUsername()));
		jsonObject.add("USER_NAME", new JsonPrimitive(user.getName()));
		jsonObject.add("USER_ID", new JsonPrimitive(user.getId()));
		jsonObject.add("USER_ROLE", new JsonPrimitive(userRole));

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObject.toString());
	}
}
