package com.solvd.lawfirm.domain.exception;

public class ProcessingException extends RuntimeException {
    public ProcessingException(String operationType, String className, String trace) {
        super(String.format("Error when try to %s object of %s class. Trace: ", operationType, className, trace));
    }
}
