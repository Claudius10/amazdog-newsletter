package com.amazdog.amazdognewsletterapi;

import com.amazdog.amazdognewsletterapi.entities.dtos.RegisterDTO;
import com.amazdog.amazdognewsletterapi.services.role.RoleService;
import com.amazdog.amazdognewsletterapi.services.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootApplication
public class AmazdogNewsletterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazdogNewsletterApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService) {
		return args -> {
			try {
				roleService.findByName("ADMINISTRADOR");
			} catch (EmptyResultDataAccessException ex) {
				roleService.create("USUARIO");
				roleService.create("ADMINISTRADOR");
				roleService.create("EDITOR");
				userService.create(new RegisterDTO(
						"Claudio",
						"bclaudiuc@gmail.com",
						"bclaudiuc@gmail.com",
						"password",
						"password"));
				userService.updateUserRole(1L, "ADMINISTRADOR", true);
				userService.updateUserRole(1L, "USUARIO", false);
			}
		};
	}
}


