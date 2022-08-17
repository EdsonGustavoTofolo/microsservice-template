package io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorAlreadyExistsException extends BusinessRuleException {
    public FornecedorAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorType.EXPT004);
    }
}
