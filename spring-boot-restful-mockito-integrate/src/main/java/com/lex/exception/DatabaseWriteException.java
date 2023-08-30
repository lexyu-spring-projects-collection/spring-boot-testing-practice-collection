package com.lex.exception;

/**
 * @author : Lex Yu
 * @date : 2023/8/30
 */
public class DatabaseWriteException extends RuntimeException {
	public DatabaseWriteException(String message) {
		super(message);
	}
}
