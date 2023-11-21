package com.amazdog.amazdognewsletterapi.repos.users;

import com.amazdog.amazdognewsletterapi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}