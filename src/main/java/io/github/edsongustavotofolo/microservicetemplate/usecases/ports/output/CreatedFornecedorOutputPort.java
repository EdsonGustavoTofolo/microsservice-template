package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;

public interface CreatedFornecedorOutputPort {
    CreatedFornecedor present(final Integer id);
    void cnpjIsInvalid() throws BusinessRuleException;
    void fornecedorAlreadyExists() throws BusinessRuleException;
}
