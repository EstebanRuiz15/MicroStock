package com.microstock.apistock.domain.exception;

public class ErrorExceptionArticleEmpty extends RuntimeException {
    public ErrorExceptionArticleEmpty (String message) {
        super(message);
    }
}
