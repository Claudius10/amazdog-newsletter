package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {

	Optional<UserDTO> findDTOByEmail(String email);

	List<UserDTO> findAllDTOByRole(String roleName);
}
