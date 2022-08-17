package io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorCnpjInvalidException extends BusinessRuleException {

    public FornecedorCnpjInvalidException() {
        super(HttpStatus.BAD_REQUEST, ErrorType.EXPT003);
    }
}
