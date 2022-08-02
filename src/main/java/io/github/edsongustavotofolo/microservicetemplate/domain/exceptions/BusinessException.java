package io.github.edsongustavotofolo.microservicetemplate.domain.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
