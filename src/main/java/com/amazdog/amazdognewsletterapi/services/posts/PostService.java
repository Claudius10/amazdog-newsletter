package com.amazdog.amazdognewsletterapi.services.posts;

import com.amazdog.amazdognewsletterapi.entities.post.Post;

import java.util.Optional;

public interface PostService {

	void create(Post post);

	Optional<Post> findById(Long id);
}
