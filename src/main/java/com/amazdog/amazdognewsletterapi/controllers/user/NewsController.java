package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.post.Post;
import com.amazdog.amazdognewsletterapi.services.posts.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
@Validated
public class NewsController {

	private final PostService postService;

	public NewsController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(params = {"page", "size"})
	public ResponseEntity<Page<Post>> findAllPaged(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAllPaged(PageRequest.of(page, size)));
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

	@PostMapping
	public ResponseEntity<String> createPost(@RequestBody @Valid Post post) {
		postService.create(post);
		return ResponseEntity.status(HttpStatus.OK).body("Post creado con éxito");
	}

	@PutMapping("/{id}/{state}")
	public ResponseEntity<?> updateState(@PathVariable Long id, @PathVariable boolean state) {
		postService.updateState(id, state);
		return ResponseEntity.status(HttpStatus.OK).body("Post creado con éxito");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		postService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
