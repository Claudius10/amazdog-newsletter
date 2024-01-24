package com.amazdog.amazdognewsletterapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "statistics")
public class Statistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime createdOn;

	@Column(nullable = false)
	@NotNull(message = "El tema/tópico no puede faltar")
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String subject;

	@Column
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String label;

	@Column(nullable = false)
	@NotNull(message = "el valor no puede faltar")
	private BigDecimal value;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Past(message = "la fecha debe ser en el pasado, no presente o futuro")
	private LocalDate date;

	@Column(nullable = false)
	@NotNull(message = "la fuente no puede faltar")
	@Size(min = 1, max = 255, message = "la longitud debe estar entre 1 y 255 caracteres")
	private String source;

	@Column
	private String tags;

	public Statistic() {
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
