package com.amazdog.amazdognewsletterapi.repos.users;

import com.amazdog.amazdognewsletterapi.entities.user.User;
import com.amazdog.amazdognewsletterapi.services.users.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

	@Modifying
	@Query("update User user set user.name = :name where user.id = :userId")
	void updateName(Long userId, String name);

	@Modifying
	@Query("update User user set user.email = :email where user.id = :userId")
	void updateEmail(Long userId, String email);

	@Modifying
	@Query("update User user set user.password = :password where user.id = :userId")
	void updatePassword(Long userId, String password);

	@Modifying
	@Query("update User user set user.isEnabled = true where user.email = :email")
	void enableAccount(String email);

	@Modifying
	@Query("update User user set user.isEnabled = false where user.email = :email")
	void desactivateAccount(String email);

	@Modifying
	@Query("update User user set user.password = :password where user.email = :email")
	void resetPassword(String email, String password);

	// info - utility

	@Query("select user.password from User user where user.id = :userId")
	String loadPassword(Long userId);

	boolean existsByEmail(String email);
}
