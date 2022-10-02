package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

@FunctionalInterface
public interface CreateFornecedorInputPort<T> {
    void execute(final CreateFornecedor fornecedorRequestModel) throws BusinessRuleException;
}
