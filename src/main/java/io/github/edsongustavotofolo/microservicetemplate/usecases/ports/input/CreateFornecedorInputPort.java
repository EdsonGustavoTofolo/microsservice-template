package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface CreateFornecedorInputPort {
    void execute(final CreateFornecedor createFornecedor) throws BusinessRuleException;
}
