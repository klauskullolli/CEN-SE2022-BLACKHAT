package com.example.BOO.Exception;

public class UnAuthenticatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnAuthenticatedException(String message) {
        super(message);
    }
}
