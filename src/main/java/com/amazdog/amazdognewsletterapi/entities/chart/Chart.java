package com.amazdog.amazdognewsletterapi.entities.chart;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "charts")
public class Chart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank(message = "El título no puede faltar")
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String title;

	@Column
	@NotBlank(message = "El tipo no puede faltar")
	private String type;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "chart_id")
	private List<Subject> subjects;

	public Chart() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
