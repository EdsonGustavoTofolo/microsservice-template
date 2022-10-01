package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorCnpjInvalidException extends BusinessRuleException {

    public FornecedorCnpjInvalidException() {
        super(HttpStatus.BAD_REQUEST, ErrorType.EXPT003);
    }
}
