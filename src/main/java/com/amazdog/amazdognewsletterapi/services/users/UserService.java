package com.amazdog.amazdognewsletterapi.services.users;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.user.User;
import jakarta.servlet.Registration;

import java.util.Optional;

public interface UserService {

	void create(RegisterDTO registerDTO);

	Optional<User> findById(Long id);
}