package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface DeleteFornecedorInputPort {
    void execute(final Integer id) throws BusinessRuleException;
}
