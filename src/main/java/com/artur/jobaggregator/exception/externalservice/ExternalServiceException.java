package com.artur.jobaggregator.exception.externalservice;

import com.artur.jobaggregator.exception.WebApplicationException;

public class ExternalServiceException extends WebApplicationException {
    public ExternalServiceException(String message) {
        super(message);
    }
}
