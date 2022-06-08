package com.example.BOO.Exception;

public class OrderNoAmountExeption extends RuntimeException{
    private static final long serialVersionUID = 2L;

    public OrderNoAmountExeption(String message) {
        super(message);
    }
}
