package com.solvd.lawfirm.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String operation, String resourceClassName, String trace) {
        super(String.format("Operation %s. Resource %s was not found. Trace: ", operation, resourceClassName, trace));
    }
}
