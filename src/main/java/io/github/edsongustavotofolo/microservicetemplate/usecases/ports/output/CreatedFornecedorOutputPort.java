package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedorModel;

public interface CreatedFornecedorOutputPort {
    CreatedFornecedorModel present(final Integer id);
    void cnpjIsInvalid() throws BusinessRuleException;
    void fornecedorAlreadyExists() throws BusinessRuleException;
}
