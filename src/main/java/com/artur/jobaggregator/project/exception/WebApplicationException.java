package com.artur.jobaggregator.project.exception;

public class WebApplicationException extends RuntimeException{
    public WebApplicationException(String message) {
        super(message);
    }
}
