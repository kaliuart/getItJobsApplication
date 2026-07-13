package com.artur.jobaggregator.exception.badrequest;

public class InvalidCredentialsException extends BadRequestException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
