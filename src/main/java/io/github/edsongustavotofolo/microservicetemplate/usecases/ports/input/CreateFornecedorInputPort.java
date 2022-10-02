package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;

@FunctionalInterface
public interface CreateFornecedorInputPort {
    CreatedFornecedor execute(final CreateFornecedor fornecedorRequestModel) throws BusinessRuleException;
}
