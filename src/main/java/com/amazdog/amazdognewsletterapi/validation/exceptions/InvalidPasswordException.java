package com.amazdog.amazdognewsletterapi.validation.exceptions;

public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}
}
