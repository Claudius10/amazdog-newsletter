package com.amazdog.amazdognewsletterapi.configs.security.keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenerator {
	public static KeyPair generateRsaKeyPair() {

		KeyPair keyPair;

		try {

			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();

		} catch (Exception e) {
			throw new IllegalStateException();
		}

		return keyPair;
	}
}
