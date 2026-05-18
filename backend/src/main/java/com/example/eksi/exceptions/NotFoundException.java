package com.example.eksi.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -561865054241300703L;

    public NotFoundException(String message) {
        super(message);
    }
}
