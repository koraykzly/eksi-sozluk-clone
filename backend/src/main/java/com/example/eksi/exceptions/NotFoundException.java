package com.example.eksi.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -561865054241300703L;

    public NotFoundException(String message) {
        super(message);
    }
}
