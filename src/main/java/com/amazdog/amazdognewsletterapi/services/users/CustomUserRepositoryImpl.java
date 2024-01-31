package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
import com.amazdog.amazdognewsletterapi.entities.dtos.UserFEDTO;
import com.amazdog.amazdognewsletterapi.entities.user.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Role> findUserRoles(String email) {
		return entityManager.createQuery("select user.roles from User user where user.email = :email", Role.class).setParameter(
				"email",
				email).getResultList();
	}

	@Override
	public Optional<UserFEDTO> findDTOByEmail(String email) {
		List<Role> roles = findUserRoles(email);
		Optional<UserDTO> user = entityManager.createQuery("""
				select new com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO(
				user.id,
				user.name,
				user.email,
				user.isEnabled
				) from User user where user.email = :email
				""", UserDTO.class).setParameter("email", email).getResultStream().findFirst();

		if (user.isEmpty()) {
			return Optional.empty();
		} else {
			UserFEDTO userFE = new UserFEDTO(user.get().id(), user.get().name(), user.get().email(), roles, user.get().isEnabled());
			return Optional.of(userFE);
		}
	}

	@Override
	public List<UserDTO> findAllDTOByRole(String roleName) {
		return entityManager.createQuery("""
						select new com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO(
						user.id,
						user.name,
						user.email,
						user.isEnabled
				) from User user join user.roles role where role.name = :roleName
				""", UserDTO.class).setParameter("roleName", roleName).getResultList();
	}
}
