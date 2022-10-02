package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FornecedorAlreadyExistsException extends BaseHttpException {

    public FornecedorAlreadyExistsException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, ErrorType.EXPT004);
    }
}
