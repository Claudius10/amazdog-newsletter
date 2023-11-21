package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.user.Role;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.amazdog.amazdognewsletterapi.repos.users.UserRepository;
import com.amazdog.amazdognewsletterapi.services.role.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleService roleService;

	private final PasswordEncoder bCryptEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder bCryptEncoder) {
		this.userRepository = userRepository;
		this.roleService = roleService;

		this.bCryptEncoder = bCryptEncoder;
	}

	@Override
	public void create(RegisterDTO registerDTO) {
		Role userRole = roleService.findByName("USUARIO").get();

		String encodedPassword = bCryptEncoder.encode(registerDTO.password());

		User user = new User.Builder()
				.withName(registerDTO.name())
				.withName(registerDTO.name())
				.withEmail(registerDTO.email())
				.withPassword(encodedPassword)
				.withRoles(userRole)
				.build();

		userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
}
