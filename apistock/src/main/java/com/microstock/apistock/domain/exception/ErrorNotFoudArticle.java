package com.microstock.apistock.domain.exception;

public class ErrorNotFoudArticle extends RuntimeException{
    public ErrorNotFoudArticle (String message) {
        super(message);
    }
}
