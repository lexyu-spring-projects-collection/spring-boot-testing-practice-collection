package com.lex.dummy;

import com.lex.service.EmailService;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class DummyEmailService implements EmailService {
	@Override
	public void sendEmail(String message) {
		throw new AssertionError("Method not implemented !!");
	}
}
