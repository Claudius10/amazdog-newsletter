package com.amazdog.amazdognewsletterapi.repos.users;

import com.amazdog.amazdognewsletterapi.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
