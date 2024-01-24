package com.amazdog.amazdognewsletterapi.repos.role;

import com.amazdog.amazdognewsletterapi.entities.user.Role;

public interface RoleRepository {

	void create(Role role);

	Role findByName(String name);
}
