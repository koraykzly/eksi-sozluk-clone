package com.example.eksi.exceptions;

import java.io.Serial;
import java.util.Map;

public class SignupException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4970045080294601529L;

    private String message;
    private Map<String, String> errors;

    public SignupException(String message) {
        this.message = message;
    }

    public SignupException(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
