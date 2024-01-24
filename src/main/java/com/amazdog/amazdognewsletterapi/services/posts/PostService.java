package com.amazdog.amazdognewsletterapi.services.posts;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {

	void create(Post post);

	Page<Post> findAllPaged(Pageable pageable);

	List<Post> findAllByActiveIsTrue();

	Optional<Post> findById(Long id);

	void updateState(Long id, boolean active);

	void deleteById(Long id);
}
