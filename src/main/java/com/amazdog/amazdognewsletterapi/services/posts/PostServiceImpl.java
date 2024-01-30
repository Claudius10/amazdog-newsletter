package com.amazdog.amazdognewsletterapi.services.posts;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import com.amazdog.amazdognewsletterapi.repos.posts.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
	public Page<Post> findAllPaged(Pageable pageable) {
		return postRepository.findAllPaged(pageable);
	}

	@Override
	public List<Post> findAllByActiveIsTrue() {
		return postRepository.findAllByActiveIsTrue();
	}

	@Override
	public Post findByIdAndActiveIsTrue(Long id) {
		return postRepository.findByIdAndActiveIsTrue(id);
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public void updateState(Long id, boolean active) {
		postRepository.updateState(id, active);
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}
}
