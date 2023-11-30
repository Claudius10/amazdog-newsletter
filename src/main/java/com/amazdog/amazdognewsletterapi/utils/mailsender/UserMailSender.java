package com.amazdog.amazdognewsletterapi.utils.mailsender;

import com.amazdog.amazdognewsletterapi.entities.Token;
import com.amazdog.amazdognewsletterapi.services.token.TokenService;
import com.amazdog.amazdognewsletterapi.services.token.TokenServiceImpl;
import com.amazdog.amazdognewsletterapi.utils.TokenUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserMailSender {

	// NOTE - set correct sender when known
	private static final String SENDER = "sender@gmail.com";

	private final JavaMailSender javaMailSender;

	private final TokenService tokenService;

	private final TokenUtils tokenUtils;


	public UserMailSender(JavaMailSender javaMailSender, TokenServiceImpl tokenService, TokenUtils tokenUtils) {
		this.javaMailSender = javaMailSender;
		this.tokenService = tokenService;
		this.tokenUtils = tokenUtils;
	}

	public void sendActivationEmail(String recipient) {
		Token token = new Token.Builder().withRecipient(recipient).build();
		Email activationEmail = EmailUtils.activationEmail(recipient, tokenUtils.encodeToken(token));

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom(SENDER);
		mailMessage.setTo(recipient);
		mailMessage.setText(activationEmail.getMsgBody());
		mailMessage.setSubject(activationEmail.getSubject());

		javaMailSender.send(mailMessage);
	}

	public void sendPwResetEmail(String recipient) {
		Token token = new Token.Builder().withRecipient(recipient).build();
		tokenService.saveToken(token);
		Email pwResetEmail = EmailUtils.pwResetEmail(recipient, tokenUtils.encodeToken(token));

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom(SENDER);
		mailMessage.setTo(recipient);
		mailMessage.setText(pwResetEmail.getMsgBody());
		mailMessage.setSubject(pwResetEmail.getSubject());

		javaMailSender.send(mailMessage);
	}
}
