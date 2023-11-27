package com.amazdog.amazdognewsletterapi.validation;

import com.amazdog.amazdognewsletterapi.validation.exceptions.InvalidPasswordException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid
			(MethodArgumentNotValidException ex,
			 HttpHeaders headers,
			 HttpStatusCode status,
			 WebRequest request) {

		List<String> errorMessages = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorMessages.add("Error del atributo " + "'" + error.getField() + "' con input: '" + error.getRejectedValue() +
					"'. Razón: " + error.getDefaultMessage());
		}

		return ResponseEntity
				.status(status)
				.body(new ApiErrorDTO.Builder(
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy")))
						.withStatusCode(status.value())
						.withPath(request.getDescription(false))
						.withErrorMsg(errorMessages)
						.build());
	}

	@ExceptionHandler({AuthenticationException.class, AccessDeniedException.class, InvalidPasswordException.class})
	protected ResponseEntity<ApiErrorDTO> handleAuthenticationExceptions(HttpServletRequest request, RuntimeException ex) {

		String errorMsg;
		if (ex instanceof BadCredentialsException) {
			errorMsg = "Email o contraseña incorrecta";
		} else {
			errorMsg = ex.getMessage();
		}

		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiErrorDTO.Builder(
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy")))
						.withStatusCode(HttpStatus.UNAUTHORIZED.value())
						.withPath(request.getServletPath())
						.withErrorMsg(List.of(errorMsg))
						.build());

	}
}