package com.lex.exception;

/**
 * @author : Lex Yu
 * @date : 2023/8/30
 */
public class DatabaseReadException extends RuntimeException {
	public DatabaseReadException(String message) {
		super(message);
	}
}
