package com.example.eksi.payload.response;

public class SignupResponse {
    private String message;
    private Long id;
    private String username;
    private String email;

    public SignupResponse(String message, Long id, String username, String email) {
        this.message = message;
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
