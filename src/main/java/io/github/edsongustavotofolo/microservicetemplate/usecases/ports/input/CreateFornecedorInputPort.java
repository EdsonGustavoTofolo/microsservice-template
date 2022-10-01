package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedorModel;

@FunctionalInterface
public interface CreateFornecedorInputPort {
    CreatedFornecedorModel execute(final CreateFornecedorModel fornecedorRequestModel) throws BusinessRuleException;
}
