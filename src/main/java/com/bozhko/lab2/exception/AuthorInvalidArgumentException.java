package com.bozhko.lab2.exception;

public class AuthorInvalidArgumentException extends RuntimeException{

    public AuthorInvalidArgumentException(String message) {
        super(message);
    }

    public AuthorInvalidArgumentException() {
    }
}
