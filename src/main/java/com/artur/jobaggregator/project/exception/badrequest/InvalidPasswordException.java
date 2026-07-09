package com.artur.jobaggregator.project.exception.badrequest;

public class InvalidPasswordException extends BadRequestException{
    public  InvalidPasswordException(String  message) {
        super(message);
    }
}
