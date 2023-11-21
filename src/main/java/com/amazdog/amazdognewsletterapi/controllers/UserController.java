package com.amazdog.amazdognewsletterapi.controllers;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
		userService.create(registerDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado con éxito");
	}
}
