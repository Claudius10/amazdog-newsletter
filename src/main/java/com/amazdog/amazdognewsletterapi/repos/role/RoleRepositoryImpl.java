package com.amazdog.amazdognewsletterapi.repos.role;

import com.amazdog.amazdognewsletterapi.entities.user.Role;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	private final EntityManager entityManager;

	public RoleRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Role role) {
		entityManager.persist(role);
	}

	@Override
	public Role findByName(String roleName) {
		return entityManager.createQuery("select role from Role role where role.name = :roleName", Role.class)
				.setParameter("roleName", roleName)
				.getSingleResult();
	}
}
