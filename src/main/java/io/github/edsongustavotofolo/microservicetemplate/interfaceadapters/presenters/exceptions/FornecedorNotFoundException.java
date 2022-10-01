package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorNotFoundException extends BusinessRuleException {

    public FornecedorNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorType.EXPT005);
    }
}
