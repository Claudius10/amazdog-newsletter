package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.UserFEDTO;
import com.amazdog.amazdognewsletterapi.entities.user.Role;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {

	List<Role> findUserRoles(String email);

	Optional<UserFEDTO> findDTOByEmail(String email);

	List<UserDTO> findAllDTOByRole(String roleName);
}
