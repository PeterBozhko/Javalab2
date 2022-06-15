package com.bozhko.lab2.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = AuthorNotFoundException.class)
    public ResponseEntity<String> AuthorNotFoundException(AuthorNotFoundException authorNotFoundException) {
        return new ResponseEntity<>(authorNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorInvalidArgumentException.class)
    public ResponseEntity<String> AuthorInvalidArgumentException(AuthorInvalidArgumentException authorInvalidArgumentException) {
        return new ResponseEntity<>(authorInvalidArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<String> BookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookInvalidArgumentException.class)
    public ResponseEntity<String> BookInvalidArgumentException(BookInvalidArgumentException bookInvalidArgumentException) {
        return new ResponseEntity<>(bookInvalidArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
