package io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorNotFoundException extends BusinessRuleException {

    public FornecedorNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorType.EXPT005);
    }
}
