package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorAlreadyExistsException extends BusinessRuleException {
    public FornecedorAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorType.EXPT004);
    }
}
