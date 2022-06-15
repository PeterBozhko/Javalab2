package com.bozhko.lab2.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException() {
    }
}
