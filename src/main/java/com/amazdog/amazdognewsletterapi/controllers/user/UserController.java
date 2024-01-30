package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.dtos.EmailChangeDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.NameChangeDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.PasswordChangeDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.PasswordDTO;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PutMapping(path = "/name", params = {"userId"})
	public ResponseEntity<String> updateUserName
			(@RequestParam Long userId,
			 @RequestBody @Valid NameChangeDTO nameChangeDTO) {
		userService.updateName(userId, nameChangeDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping(path = "/email", params = {"userId"})
	public ResponseEntity<String> updateUserEmail
			(@RequestParam Long userId,
			 @RequestBody @Valid EmailChangeDTO emailChangeDTO) {
		userService.updateEmail(userId, emailChangeDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping(path = "/password", params = {"userId"})
	public ResponseEntity<String> updateUserPassword
			(@RequestParam Long userId,
			 @RequestBody @Valid PasswordChangeDTO passwordChangeDTO) {
		userService.updatePassword(userId, passwordChangeDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(params = {"userId"})
	public ResponseEntity<String> deleteUserAccount
			(@RequestParam Long userId,
			 @Valid @RequestBody PasswordDTO passwordDTO) {
		userService.deleteAccount(userId, passwordDTO.password());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
