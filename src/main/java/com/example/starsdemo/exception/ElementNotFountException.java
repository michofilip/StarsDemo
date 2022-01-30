package com.example.starsdemo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class ElementNotFountException extends RuntimeException {
    public ElementNotFountException() {
        super();
    }

    public ElementNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFountException(String message) {
        super(message);
    }

    public ElementNotFountException(Throwable cause) {
        super(cause);
    }
}
