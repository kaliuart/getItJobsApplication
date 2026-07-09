package com.artur.jobaggregator.project.exception.badrequest;

public class InvalidEmailException extends BadRequestException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
