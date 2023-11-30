package com.amazdog.amazdognewsletterapi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "password_token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private LocalDateTime expiresOn;

	private String recipient;

	public Token() {
	}

	private Token(Builder builder) {
		this.expiresOn = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
		this.recipient = builder.recipient;
	}

	public static class Builder {

		private String recipient;

		public Builder() {
		}

		public Builder withRecipient(String recipient) {
			this.recipient = recipient;
			return this;
		}

		public Token build() {
			return new Token(this);
		}
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
