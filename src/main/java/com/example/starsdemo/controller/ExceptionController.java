package com.example.starsdemo.controller;

import com.example.starsdemo.exception.ElementNotFountException;
import com.example.starsdemo.exception.ForbiddenPlanetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(ElementNotFountException.class)
//    public ResponseEntity<String> handleElementNotFountException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(ForbiddenPlanetException.class)
//    public ResponseEntity<String> handleForbiddenPlanetException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler({ElementNotFountException.class, ForbiddenPlanetException.class})
    public ResponseEntity<String> handleCustomException(Exception exception, WebRequest request) throws Exception {
        return switch (exception) {
            case ElementNotFountException ex -> new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
            case ForbiddenPlanetException ex -> new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
            case default -> throw exception;
        };
    }

}
