package com.example.BOO.Exception;

public class DublicatedOrderExeption extends  RuntimeException{

    private static final long serialVersionUID = 2L;

    public DublicatedOrderExeption(String message) {
        super(message);
    }
}
