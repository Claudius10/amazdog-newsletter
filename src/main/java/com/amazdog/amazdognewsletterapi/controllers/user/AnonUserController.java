package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anon/user")
public class AnonUserController {

	private final UserService userService;

	public AnonUserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
		try {
			userService.create(registerDTO);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.OK).body("Una cuenta ya existe con el correo electrónico " + registerDTO.email());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado con éxito");
	}

	// TODO -- pw reset
}
