package com.artur.jobaggregator.exception.notfound;

import com.artur.jobaggregator.exception.WebApplicationException;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}
