package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
import com.amazdog.amazdognewsletterapi.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	void create(RegisterDTO registerDTO);

	Optional<User> findById(Long id);

	Optional<UserDTO> findDTOByEmail(String email);

	List<UserDTO> findAllDTOByRole(String roleName);

	String updateUserRole(Long userId, String roleName, boolean add);

}
