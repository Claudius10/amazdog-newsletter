package com.amazdog.amazdognewsletterapi.entities.dtos;

import jakarta.validation.constraints.Pattern;

public record NameChangeDTO(
		@Pattern(
				regexp = "^[a-zA-Z\sÁÉÍÓÚáéíóúÑñ]{2,50}$",
				message = "Formato inválido. Ejemplo formato válido: José Miguel")
		String name,
		String password) {
}
