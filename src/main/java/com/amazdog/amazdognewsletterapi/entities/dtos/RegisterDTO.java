package com.amazdog.amazdognewsletterapi.entities.dtos;

import com.amazdog.amazdognewsletterapi.validation.constraints.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@FieldMatch.List({
		@FieldMatch(first = "email", second = "matchingEmail", message = "El email debe coincidir"),
		@FieldMatch(first = "password", second = "matchingPassword", message = "La contraseña debe coincidir")})
public record RegisterDTO(
		@Pattern(
				regexp = "^[a-zA-Z\sÁÉÍÓÚáéíóúÑñ]{2,50}$",
				message = "Formato inválido. Ejemplo formato válido: José Miguel")
		String name,

		@Email(message = "Formato inválido. Ejemplo formato válido: correos15@gmail.com")
		@NotBlank(message = "El email no puede faltar")
		String email,

		@Email(message = "Formato inválido. Ejemplo formato válido: correos15@gmail.com")
		@NotBlank(message = "El email no puede faltar")
		String matchingEmail,

		@Size(min = 8, max = 20, message = "La contraseña tiene que contener entre 8-20 caracteres")
		@Pattern(
				regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
				message = "Mínimo 8 caracteres, una letra mayúscula, una letra minúscula, y un número")
		String password,

		@Size(min = 8, max = 20, message = "La contraseña tiene que contener entre 8-20 caracteres")
		@Pattern(
				regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
				message = "Mínimo 8 caracteres, una letra mayúscula, una letra minúscula, y un número")
		String matchingPassword) {
}
