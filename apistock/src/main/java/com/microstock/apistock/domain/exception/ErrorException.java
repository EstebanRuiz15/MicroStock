package com.microstock.apistock.domain.exception;

public class ErrorException extends RuntimeException {
    public ErrorException (String message) {
            super(message);
        }

}
