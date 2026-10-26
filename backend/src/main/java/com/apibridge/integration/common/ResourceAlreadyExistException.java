package com.apibridge.integration.common;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
