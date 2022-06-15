package com.bozhko.lab2.exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException() {
    }
}
