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

}
