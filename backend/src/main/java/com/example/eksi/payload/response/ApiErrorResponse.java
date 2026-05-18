package com.example.eksi.payload.response;

import java.util.Map;

public class ApiErrorResponse {
    private String message;
    private int status;
    private String path;
    private Map<String, String> errors;

    public ApiErrorResponse(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public ApiErrorResponse(String message, int status, String path, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
