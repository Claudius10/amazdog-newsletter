package com.amazdog.amazdognewsletterapi.services.role;

import com.amazdog.amazdognewsletterapi.entities.user.Role;
import com.amazdog.amazdognewsletterapi.repos.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void create(String roleName) {
		roleRepository.create(new Role(roleName));
	}

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName);
	}
}
