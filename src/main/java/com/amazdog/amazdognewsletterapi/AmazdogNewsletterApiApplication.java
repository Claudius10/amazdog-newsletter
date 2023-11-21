package com.amazdog.amazdognewsletterapi;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.entities.user.Role;
import com.amazdog.amazdognewsletterapi.services.role.RoleService;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class AmazdogNewsletterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazdogNewsletterApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService) {
		return args -> {
			Optional<Role> userRole = roleService.findByName("ADMIN");
			if (userRole.isEmpty()) {
				roleService.create("ADMIN");
				roleService.create("USUARIO");
				roleService.create("EDITOR");
				userService.create(new RegisterDTO(
						"Clau",
						"clau@gmail.com",
						"clau@gmail.com",
						"password",
						"password"));
			}
		};
	}
}


