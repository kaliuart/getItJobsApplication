package com.artur.jobaggregator.project.exception.conflict;

import com.artur.jobaggregator.project.exception.WebApplicationException;

public class ConflictException extends WebApplicationException {
    public ConflictException(String message) {
        super(message);
    }
}
