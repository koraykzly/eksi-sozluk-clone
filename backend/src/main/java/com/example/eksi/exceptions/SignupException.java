package com.example.eksi.exceptions;

public class SignupException extends RuntimeException {

    private static final long serialVersionUID = 4970045080294601529L;

    private String message;

    public SignupException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

}
