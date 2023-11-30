package com.amazdog.amazdognewsletterapi.utils.mailsender;

public class Email {

	private String recipient, msgBody, subject, attachment;

	public Email() {
	}

	private Email(Builder builder) {
		this.recipient = builder.recipient;
		this.msgBody = builder.msgBody;
		this.subject = builder.subject;
		this.attachment = builder.attachment;
	}

	public static class Builder {

		private String recipient, msgBody, subject, attachment;

		public Builder() {
		}

		public Builder withRecipient(String recipient) {
			this.recipient = recipient;
			return this;
		}

		public Builder withMessage(String message) {
			this.msgBody = message;
			return this;
		}

		public Builder withSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder withAttachment(String attachment) {
			this.attachment = attachment;
			return this;
		}

		public Email build() {
			return new Email(this);
		}
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
