package com.example.eksi.exceptions;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8297861936584458774L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
