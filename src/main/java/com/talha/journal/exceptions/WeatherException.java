package com.talha.journal.exceptions;

import org.springframework.http.HttpStatus;

public class WeatherException extends RuntimeException {
    private final HttpStatus statusCode;

    public WeatherException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

