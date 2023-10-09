package com.example.eksi.exceptions;


public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -561865054241300703L;

	public UserNotFoundException(String message) {
        super(message);
    }
}
