package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorCnpjInvalidException extends BaseHttpException {

    public FornecedorCnpjInvalidException() {
        super(HttpStatus.BAD_REQUEST, ErrorType.EXPT003);
    }
}
