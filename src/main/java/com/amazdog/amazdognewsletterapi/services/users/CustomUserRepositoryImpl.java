package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO;
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
	public Optional<UserDTO> findDTOByEmail(String email) {
		return entityManager.createQuery("""
				select new com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO(
				user.id,
				user.name,
				user.email
				) from User user where user.email = :email
				""", UserDTO.class).setParameter("email", email).getResultStream().findFirst();
	}

	@Override
	public List<UserDTO> findAllDTOByRole(String roleName) {
		return entityManager.createQuery("""
						select new com.amazdog.amazdognewsletterapi.entities.dtos.UserDTO(
						user.id,
						user.name,
						user.email
				) from User user join user.roles role where role.name = :roleName
				""", UserDTO.class).setParameter("roleName", roleName).getResultList();
	}
}
