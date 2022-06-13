package com.example.BOO.Exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 3L;

    public BadRequestException(String message){

        super(message);
    }
}
