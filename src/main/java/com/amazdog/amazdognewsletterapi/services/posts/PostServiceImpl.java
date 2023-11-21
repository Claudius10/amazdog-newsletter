package com.amazdog.amazdognewsletterapi.services.posts;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import com.amazdog.amazdognewsletterapi.repos.posts.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void create(Post post) {
		post.setCreatedOn(LocalDateTime.now());
		postRepository.save(post);
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}
}
