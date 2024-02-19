package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.Token;
import com.amazdog.amazdognewsletterapi.entities.dtos.*;
import com.amazdog.amazdognewsletterapi.entities.user.Role;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.amazdog.amazdognewsletterapi.repos.users.UserRepository;
import com.amazdog.amazdognewsletterapi.services.role.RoleService;
import com.amazdog.amazdognewsletterapi.services.token.TokenService;
import com.amazdog.amazdognewsletterapi.utils.TokenUtils;
import com.amazdog.amazdognewsletterapi.utils.mailsender.UserMailSender;
import com.amazdog.amazdognewsletterapi.validation.exceptions.InvalidPasswordException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleService roleService;

	private final TokenService tokenService;

	private final UserMailSender userMailSender;

	private final TokenUtils tokenUtils;

	private final PasswordEncoder bCryptEncoder;

	public UserServiceImpl
			(UserRepository userRepository,
			 RoleService roleService,
			 TokenService tokenService, UserMailSender userMailSender,
			 TokenUtils tokenUtils,
			 PasswordEncoder bCryptEncoder) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.tokenService = tokenService;
		this.userMailSender = userMailSender;
		this.tokenUtils = tokenUtils;
		this.bCryptEncoder = bCryptEncoder;
	}

	// info - for anon user

	@Override
	public void create(RegisterDTO registerDTO) {
		Role userRole = roleService.findByName("ADMINISTRADOR");

		String encodedPassword = bCryptEncoder.encode(registerDTO.password());

		User user = new User.Builder()
				.withName(registerDTO.name())
				.withName(registerDTO.name())
				.withEmail(registerDTO.email())
				.withPassword(encodedPassword)
				.withRoles(userRole)
				.build();

		userRepository.save(user);
		userMailSender.sendActivationEmail(registerDTO.email());
	}

	@Override
	public String activateAccount(String serializedToken) {
		Token activationToken = tokenUtils.decodeToken(serializedToken);

		if (LocalDateTime.now().isAfter(activationToken.getExpiresOn())) {
			userMailSender.sendActivationEmail(activationToken.getRecipient());
			return "El enlace de activación caducó. Recibirá uno nuevo en breve";
		} else {
			userRepository.enableAccount(activationToken.getRecipient());
			return "Cuenta activada con éxito";
		}
	}

	// info - for user role

	@Override
	public void updateName(Long userId, NameChangeDTO nameChangeDTO) {
		verifyPassword(userId, nameChangeDTO.password());
		userRepository.updateName(userId, nameChangeDTO.name());
	}

	@Override
	public void updateEmail(Long userId, EmailChangeDTO emailChangeDTO) {
		verifyPassword(userId, emailChangeDTO.password());
		userRepository.updateEmail(userId, emailChangeDTO.email());
	}

	@Override
	public void updatePassword(Long userId, PasswordChangeDTO passwordChangeDTO) {
		verifyPassword(userId, passwordChangeDTO.currentPassword());
		String encodedPassword = bCryptEncoder.encode(passwordChangeDTO.newPassword());
		userRepository.updatePassword(userId, encodedPassword);
	}

	@Override
	public void deleteAccount(Long userId, String password) {
		verifyPassword(userId, password);
		userRepository.deleteById(userId);
	}

	@Override
	public void passwordReset(String resetToken, PasswordResetDTO passwordResetDTO) {
		Token pwResetToken = tokenUtils.decodeToken(resetToken);
		if (tokenService.tokenExists(pwResetToken.getId())) {
			String encodedPassword = bCryptEncoder.encode(passwordResetDTO.newPassword());
			userRepository.resetPassword(pwResetToken.getRecipient(), encodedPassword);
			tokenService.deleteToken(pwResetToken.getId());
		} else {
			throw new AccessDeniedException("Access Denied: fraudulent token");
		}
	}

	// info - for admin role

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<UserFEDTO> findDTOByEmail(String email) {
		return userRepository.findDTOByEmail(email);
	}

	@Override
	public List<UserDTO> findAllDTOByRole(String roleName) {
		return userRepository.findAllDTOByRole(roleName);
	}

	@Override
	public String updateUserRole(Long userId, String roleName, boolean add) {
		Role role = roleService.findByName(roleName);
		Optional<User> user = findById(userId);

		if (user.isPresent()) {
			if (add) {
				// check if user already has the role
				for (Role theRole : user.get().getAuthorities()) {
					if (theRole.getAuthority().equals(roleName)) {
						return "El usuario " + user.get().getUsername() + " ya posee el privilegio '" + roleName + "'";
					}
				}
				// add the role if it doesn't
				user.get().getAuthorities().add(role);
				return "Privilegio '" + roleName + "' añadido con éxito para " + user.get().getUsername();
			} else {
				if (!user.get().getAuthorities().contains(new Role(roleName))) {
					return "El usuario " + user.get().getUsername() + " no posee el privilegio '" + roleName + "'";
				} else {
					// remove the role
					user.get().getAuthorities().remove(role);
					return "Privilegio '" + roleName + "' eliminado con éxito para " + user.get().getUsername();
				}
			}
		} else {
			return "No se encontró el usuario con id " + userId;
		}
	}

	@Override
	public void desactivateAccount(String email) {
		userRepository.desactivateAccount(email);
	}

	@Override
	public void reenableAccount(String email) {
		userRepository.enableAccount(email);
	}

	// info - util method

	@Override
	public String loadPassword(Long userId) {
		return userRepository.loadPassword(userId);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	private void verifyPassword(Long userId, String password) {
		if (!bCryptEncoder.matches(password, loadPassword(userId))) {
			throw new InvalidPasswordException("La contraseña proporcionada no coincide con la contraseña almacenada");
		}
	}
}
