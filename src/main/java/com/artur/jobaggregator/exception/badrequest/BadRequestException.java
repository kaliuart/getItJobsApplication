package com.artur.jobaggregator.exception.badrequest;

import com.artur.jobaggregator.exception.WebApplicationException;

public class BadRequestException extends WebApplicationException {
    public BadRequestException(String message) {
        super(message);
    }
}
