package com.solvd.lawfirm.domain.exception;

public class ResourceNotFoundException extends Exception {
    
    public ResourceNotFoundException(String message) {
        super(String.format(message));
    }
}
