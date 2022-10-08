package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

@FunctionalInterface
public interface UpdateFornecedorInputPort {
    void execute(final Integer id, final UpdateFornecedor updateFornecedor) throws BusinessRuleException;
}
