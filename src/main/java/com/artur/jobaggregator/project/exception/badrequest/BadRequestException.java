package com.artur.jobaggregator.project.exception.badrequest;

import com.artur.jobaggregator.project.exception.WebApplicationException;

public class BadRequestException extends WebApplicationException {
    public BadRequestException(String message) {
        super(message);
    }
}
