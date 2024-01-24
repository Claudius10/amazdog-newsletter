package com.amazdog.amazdognewsletterapi.services.role;

import com.amazdog.amazdognewsletterapi.entities.user.Role;

import java.util.Optional;

public interface RoleService {

	void create(String roleName);

	Role findByName(String roleName);
}
