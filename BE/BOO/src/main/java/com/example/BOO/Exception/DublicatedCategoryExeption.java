package com.example.BOO.Exception;

public class DublicatedCategoryExeption extends RuntimeException {

    private static final long serialVersionUID = 3L;

    public DublicatedCategoryExeption(String message){

        super(message);
    }
}
