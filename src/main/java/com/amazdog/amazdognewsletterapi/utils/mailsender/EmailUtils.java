package com.amazdog.amazdognewsletterapi.utils.mailsender;

import org.springframework.stereotype.Component;

@Component
public final class EmailUtils {

	private EmailUtils() {
	}

	public static Email activationEmail(String recipient, String token) {
		String activationLink = "https://amazdog-newsletter-fe-production.up.railway.app/activate/" + token;

		return new Email.Builder()
				.withRecipient(recipient)
				.withSubject("¡Bienvenido a Amazdog Newsletter!")
				.withMessage("Para activar tu cuenta pulsa el enlace: " + activationLink)
				.build();
	}

	public static Email pwResetEmail(String recipient, String token) {
		String resetPwLink = "https://amazdog-newsletter-fe-production.up.railway.app/password-reset/" + token;

		return new Email.Builder()
				.withRecipient(recipient)
				.withSubject("Restablecer contraseña de Amazdog Newsletter")
				.withMessage("Para cambiar la contraseña pulse el siguiente enlace: " + resetPwLink)
				.build();
	}
}
