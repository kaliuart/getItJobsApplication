package com.artur.jobaggregator.project.exception.externalservice;

import com.artur.jobaggregator.project.exception.WebApplicationException;

public class ExternalServiceException extends WebApplicationException {
    public ExternalServiceException(String message) {
        super(message);
    }
}
