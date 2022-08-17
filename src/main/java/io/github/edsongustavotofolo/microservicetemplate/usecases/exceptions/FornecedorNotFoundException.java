package io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorNotFoundException extends BusinessRuleException {

    public FornecedorNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorType.EXPT005);
    }
}
