package com.artur.jobaggregator.exception.conflict;

import com.artur.jobaggregator.exception.WebApplicationException;

public class ConflictException extends WebApplicationException {
    public ConflictException(String message) {
        super(message);
    }
}
