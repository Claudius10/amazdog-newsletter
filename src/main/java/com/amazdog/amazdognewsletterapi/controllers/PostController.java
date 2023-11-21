package com.amazdog.amazdognewsletterapi.controllers;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import com.amazdog.amazdognewsletterapi.services.posts.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@Validated
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody @Valid Post post) {
		postService.create(post);
		return ResponseEntity.status(HttpStatus.OK).body("Post creado con éxito");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findPostById(@PathVariable Long id) {
		Optional<Post> post = postService.findById(id);
		if (post.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay resultados para post con id " + id);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(post.get());
		}
	}
}
