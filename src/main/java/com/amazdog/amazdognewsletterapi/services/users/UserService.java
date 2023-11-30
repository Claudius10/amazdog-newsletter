package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.*;
import com.amazdog.amazdognewsletterapi.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	// info - for anon user

	void create(RegisterDTO registerDTO);

	String activateAccount(String activationToken);

	// info - for user role

	void updateName(Long userId, NameChangeDTO nameChangeDTO);

	void updateEmail(Long userId, EmailChangeDTO emailChangeDTO);

	void updatePassword(Long userId, PasswordChangeDTO passwordChangeDTO);

	void deleteAccount(Long userId, String password);

	void passwordReset(String resetToken, PasswordResetDTO passwordResetDTO);

	// info - for admin role

	Optional<User> findById(Long id);

	Optional<UserDTO> findDTOByEmail(String email);

	List<UserDTO> findAllDTOByRole(String roleName);

	String updateUserRole(Long userId, String roleName, boolean add);

	// info - util method

	String loadPassword(Long userId);

}
