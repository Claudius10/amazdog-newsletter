package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(params = "roleName")
	public ResponseEntity<List<UserDTO>> findAllUserDTOByRole(@RequestParam String roleName) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAllDTOByRole(roleName));
	}

	@GetMapping(params = "userEmail")
	public ResponseEntity<?> findUserDTOByEmail(@RequestParam String userEmail) {
		Optional<UserDTO> userDTO = userService.findDTOByEmail(userEmail);
		if (userDTO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("No hay resultados para usuario con email " + userEmail);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(userDTO);
		}
	}

	@PutMapping()
	public ResponseEntity<String> updateUserRole
			(@RequestParam Long userId,
			 @RequestParam String roleName,
			 @RequestParam boolean add) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserRole(userId, roleName, add));
	}
}
