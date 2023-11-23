package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
import com.amazdog.amazdognewsletterapi.entities.user.Role;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.amazdog.amazdognewsletterapi.repos.users.UserRepository;
import com.amazdog.amazdognewsletterapi.services.role.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleService roleService;

	private final PasswordEncoder bCryptEncoder;

	public UserServiceImpl
			(UserRepository userRepository,
			 RoleService roleService,
			 PasswordEncoder bCryptEncoder) {
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

	@Override
	public Optional<UserDTO> findDTOByEmail(String email) {
		return userRepository.findDTOByEmail(email);
	}

	@Override
	public List<UserDTO> findAllDTOByRole(String roleName) {
		return userRepository.findAllDTOByRole(roleName);
	}

	@Override
	public String updateUserRole(Long userId, String roleName, boolean add) {
		Optional<Role> role = roleService.findByName(roleName);
		Optional<User> user = findById(userId);

		if (role.isEmpty()) {
			return "No se encontró el privilegio " + roleName;
		} else {
			if (user.isPresent()) {
				if (add) {
					// check if user already has the role
					for (Role theRole : user.get().getAuthorities()) {
						if (theRole.getAuthority().equals(roleName)) {
							return "El usuario con email " + user.get().getUsername() + " ya posee el privilegio '" + roleName + "'";
						}
					}
					// add the role if it doesn't
					user.get().getAuthorities().add(role.get());
					return "Privilegio '" + roleName + "' añadido con éxito para " + user.get().getUsername();
				} else {
					// remove the role
					user.get().getAuthorities().remove(role.get());
					return "Privilegio '" + roleName + "' eliminado con éxito para " + user.get().getUsername();
				}
			} else {
				return "No se encontró el usuario con id " + userId;
			}
		}
	}

}
