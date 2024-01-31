package com.amazdog.amazdognewsletterapi.repos.posts;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("select p from Post p order by p.createdOn")
	Page<Post> findAllPaged(Pageable pageable);

	@Modifying
	@Query("update Post post set post.active = :active where post.id = :id")
	void updateState(Long id, boolean active);

	@Query("select p from Post p where p.active = true")
	List<Post> findAllActiveNews();

	@Query("select p from Post p where p.active = true and p.id = :id")
	Post findActiveNews(Long id);
}
