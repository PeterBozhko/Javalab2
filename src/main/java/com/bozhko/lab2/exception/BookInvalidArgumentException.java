package com.bozhko.lab2.exception;

public class BookInvalidArgumentException extends RuntimeException{

    public BookInvalidArgumentException(String message) {
        super(message);
    }

    public BookInvalidArgumentException() {
    }
}
