package com.example.starsdemo.exception;

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
