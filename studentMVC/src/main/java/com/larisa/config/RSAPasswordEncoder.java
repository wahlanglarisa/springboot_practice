package com.larisa.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RSAPasswordEncoder implements PasswordEncoder {

	private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	@Override
	public String encode(CharSequence rawPassword) {

		try {
			String password = rawPassword.toString();
			System.out.println("Encode called");
			if (password.startsWith("userNotFoundPassword")) {
				return bcrypt.encode(password);
			}
			String decrypted = RSADecryptUtil.decrypt(rawPassword.toString());
			return bcrypt.encode(decrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		try {

			String decrypted = RSADecryptUtil.decrypt(rawPassword.toString());
			System.out.println("Decrypted Password " + decrypted + " Encrypted password" + rawPassword);
			return bcrypt.matches(decrypted, encodedPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
