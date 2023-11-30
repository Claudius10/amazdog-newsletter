package com.amazdog.amazdognewsletterapi.entities.dtos;

import com.amazdog.amazdognewsletterapi.validation.constraints.FieldMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldMatch.List({
		@FieldMatch(
				first = "newPassword",
				second = "matchingNewPassword",
				message = "La contraseña debe coincidir")})
public record PasswordResetDTO(
		@Size(min = 8, max = 20, message = "La contraseña tiene que contener entre 8-20 caracteres")
		@NotBlank(message = "La nueva contraseña no puede faltar")
		String newPassword,

		@Size(min = 8, max = 20, message = "La contraseña tiene que contener entre 8-20 caracteres")
		@NotBlank(message = "La nueva contraseña no puede faltar")
		String matchingNewPassword) {
}
