package com.example.eksi.exceptions;


public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -8297861936584458774L;

	public UnauthorizedException(String message) {
        super(message);
    }
}
