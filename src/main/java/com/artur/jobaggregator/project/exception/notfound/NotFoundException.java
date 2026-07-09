package com.artur.jobaggregator.project.exception.notfound;

import com.artur.jobaggregator.project.exception.WebApplicationException;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}
