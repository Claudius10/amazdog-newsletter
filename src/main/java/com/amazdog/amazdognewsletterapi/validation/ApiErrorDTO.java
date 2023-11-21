package com.amazdog.amazdognewsletterapi.validation;

import java.util.List;

public record ApiErrorDTO

		(String timesStamp,
		 int statusCode,
		 String path,
		 List<String> errorMsg) {

	private ApiErrorDTO(Builder builder) {
		this(builder.timeStamp, builder.statusCode, builder.path, builder.errorMsgs);
	}

	public static class Builder {

		private final String timeStamp;

		private int statusCode;

		private String path;

		private List<String> errorMsgs;

		public Builder(String timeStamp) {
			if (timeStamp == null) {
				throw new IllegalStateException("Timestamp cannot be null.");
			}
			this.timeStamp = timeStamp;
		}

		public Builder withStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder withPath(String path) {
			this.path = path;
			return this;
		}

		public Builder withErrorMsg(List<String> errorMsgs) {
			this.errorMsgs = errorMsgs;
			return this;
		}

		public ApiErrorDTO build() {
			return new ApiErrorDTO(this);
		}
	}
}