package com.amazdog.amazdognewsletterapi.configs.security;

import com.amazdog.amazdognewsletterapi.configs.security.login.InvalidAuthHandler;
import com.amazdog.amazdognewsletterapi.configs.security.login.ValidAuthHandler;
import com.amazdog.amazdognewsletterapi.configs.security.oauth2.OAuth2AccessDeniedHandler;
import com.amazdog.amazdognewsletterapi.configs.security.oauth2.OAuth2AuthEntryPoint;
import com.amazdog.amazdognewsletterapi.configs.security.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

	private final JWTUtils jwtUtils;

	private final OAuth2AccessDeniedHandler restAccessDeniedHandler;

	private final OAuth2AuthEntryPoint restAuthenticationEntryPoint;

	private final ValidAuthHandler validAuthHandler;

	private final InvalidAuthHandler invalidAuthHandler;

	public SecurityConfig
			(JWTUtils jwtUtils,
			 OAuth2AccessDeniedHandler restAccessDeniedHandler,
			 OAuth2AuthEntryPoint restAuthenticationEntryPoint,
			 ValidAuthHandler validAuthHandler,
			 InvalidAuthHandler invalidAuthHandler) {
		this.jwtUtils = jwtUtils;
		this.restAccessDeniedHandler = restAccessDeniedHandler;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.validAuthHandler = validAuthHandler;
		this.invalidAuthHandler = invalidAuthHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		// JWT support config
		httpSecurity.oauth2ResourceServer(oauth2ResourceServer -> {
			oauth2ResourceServer.jwt(jwt -> {
				jwt.decoder(jwtUtils.jwtDecoder());
				jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
			});
			oauth2ResourceServer.authenticationEntryPoint(restAuthenticationEntryPoint);
			oauth2ResourceServer.accessDeniedHandler(restAccessDeniedHandler);
		});


		// endpoints config
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/api/anon/user/**").permitAll();
			auth.requestMatchers("/api/resources/**").permitAll();
			auth.requestMatchers("/api/user/**").hasAnyRole("ADMINISTRADOR", "EDITOR", "USUARIO");
			auth.requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR");
			auth.requestMatchers("/api/charts/**").hasAnyRole("ADMINISTRADOR", "EDITOR");
			auth.requestMatchers("/api/news/**").hasAnyRole("ADMINISTRADOR", "EDITOR");
			auth.requestMatchers("/api/statistics/**").hasAnyRole("ADMINISTRADOR", "EDITOR");
			auth.anyRequest().authenticated();
		});

		// authenticate
		httpSecurity.formLogin(formLogin -> {
			formLogin.loginPage("/api/auth/login");
			formLogin.successHandler(validAuthHandler);
			formLogin.failureHandler(invalidAuthHandler);
		});

		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));
		return httpSecurity.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(
				Arrays.asList("https://amazdog-newsletter-fe-production.up.railway.app/", "http://192.168.0.10:3000", "http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		configuration.setExposedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authenticationProvider);
	}

	// change the auto added prefix to roles from SCOPE_ to ROLE_
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtConverter;
	}
}
