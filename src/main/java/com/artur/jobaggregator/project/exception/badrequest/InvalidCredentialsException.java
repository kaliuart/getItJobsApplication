package com.artur.jobaggregator.project.exception.badrequest;

public class InvalidCredentialsException extends BadRequestException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
