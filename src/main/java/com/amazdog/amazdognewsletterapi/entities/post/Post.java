package com.amazdog.amazdognewsletterapi.entities.post;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime createdOn;

	@Column(nullable = false)
	@NotBlank(message = "el título no puede faltar")
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	@NotBlank(message = "El texto no puede faltar")
	private String text;

	@Column
	private String mainImage;

	@Type(JsonType.class)
	@Column(columnDefinition = "json")
	private List<ImageGallery> images = new ArrayList<>();

	@Column
	private String keywords;

	@Column(nullable = false)
	@NotBlank(message = "El autor no puede faltar")
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String author;

	@Column
	private String link;

	public Post() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public List<ImageGallery> getImages() {
		return images;
	}

	public void setImages(List<ImageGallery> images) {
		this.images = images;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
