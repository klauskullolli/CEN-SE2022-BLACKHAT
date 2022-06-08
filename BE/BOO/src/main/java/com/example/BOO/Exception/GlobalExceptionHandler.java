package com.example.BOO.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handling specific exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDetails resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return errorDetails;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DublicatedCategoryExeption.class)
    public ErrorDetails dublicatedCategoryHandling(DublicatedCategoryExeption ex , WebRequest request){
                ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
                return  errorDetails;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DublicatedOrderExeption.class)
    public ErrorDetails dublicatedOrderHandling(DublicatedOrderExeption ex , WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return  errorDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNoAmountExeption.class)
    public ErrorDetails OrderNoAmountHandling(OrderNoAmountExeption ex , WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return  errorDetails;
    }

}
