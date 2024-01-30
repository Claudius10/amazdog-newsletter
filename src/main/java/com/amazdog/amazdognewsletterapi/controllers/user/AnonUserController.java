package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.dtos.PasswordResetDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import com.amazdog.amazdognewsletterapi.utils.mailsender.UserMailSender;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anon/user")
public class AnonUserController {

	private final UserService userService;

	private final UserMailSender userMailSender;

	public AnonUserController(UserService userService, UserMailSender userMailSender) {
		this.userService = userService;
		this.userMailSender = userMailSender;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
		try {
			userService.create(registerDTO);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.OK).body("Una cuenta ya existe con el correo electrónico " + registerDTO.email());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado con éxito");
	}

	@PostMapping(path = "activate", params = "token")
	public ResponseEntity<String> activateUserAccount(@RequestParam String token) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.activateAccount(token));
	}

	@PostMapping(path = "/password", params = "resetFor")
	public ResponseEntity<String> requestPwReset(@RequestParam String resetFor) {
		boolean emailExists = userService.existsByEmail(resetFor);
		if (emailExists) {
			userMailSender.sendPwResetEmail(resetFor);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El correo electrónico proporcionado no existe en la base de " +
					"datos");
		}
	}

	@PostMapping(path = "/password", params = "resetToken")
	public ResponseEntity<String> resetUserPw(@RequestParam String resetToken, @RequestBody @Valid PasswordResetDTO passwordResetDTO) {
		userService.passwordReset(resetToken, passwordResetDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
