package com.microstock.apistock.domain.exception;

public class ErrorExceptionParam extends RuntimeException{
    public ErrorExceptionParam (String message) {
        super(message);
    }
    
}
