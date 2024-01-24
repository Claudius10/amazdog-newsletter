package com.amazdog.amazdognewsletterapi.utils.mailsender;

import org.springframework.stereotype.Component;

@Component
public final class EmailUtils {

	private EmailUtils() {
	}

	public static Email activationEmail(String recipient, String token) {
		// NOTE - set correct link when known
		String activationLink = "http://192.168.0.10:3000/activate/" + token;

		return new Email.Builder()
				.withRecipient(recipient)
				.withSubject("¡Bienvenido a Amazdog Newsletter!")
				.withMessage("Para activar tu cuenta pulsa el enlace: " + activationLink)
				.build();
	}

	public static Email pwResetEmail(String recipient, String token) {
		// NOTE - set correct link when known
		String resetPwLink = "http://localhost:8080/api/anon/user/password?resetToken=" + token;

		return new Email.Builder()
				.withRecipient(recipient)
				.withSubject("Restablecer contraseña de Amazdog Newsletter")
				.withMessage("Para cambiar la contraseña pulse el siguiente enlace: " + resetPwLink)
				.build();
	}
}
