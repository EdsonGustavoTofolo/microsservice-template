package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import lombok.Getter;

@Getter
public class BusinessRuleException extends Exception {

    private final ErrorType errorType;

    public BusinessRuleException(final ErrorType errorType, final Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }

    public BusinessRuleException(final ErrorType errorType) {
        this.errorType = errorType;
    }
}
