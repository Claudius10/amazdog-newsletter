package com.amazdog.amazdognewsletterapi.entities.dtos;

import jakarta.validation.constraints.NotBlank;

public record PasswordDTO(@NotBlank(message = "La contraseña no puede faltar") String password) {
}
