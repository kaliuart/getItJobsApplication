package com.artur.jobaggregator.project.exception.conflict;

public class EmailAlreadyExistsException extends ConflictException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
