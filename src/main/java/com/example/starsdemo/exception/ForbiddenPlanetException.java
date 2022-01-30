package com.example.starsdemo.exception;

public class ForbiddenPlanetException extends RuntimeException {
    public ForbiddenPlanetException() {
        super();
    }

    public ForbiddenPlanetException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenPlanetException(String message) {
        super(message);
    }

    public ForbiddenPlanetException(Throwable cause) {
        super(cause);
    }
}
