package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorNotFoundException extends BaseHttpException {

    public FornecedorNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorType.EXPT005);
    }
}
