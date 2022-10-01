package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreatedFornecedorModel;

public interface CreatedFornecedorOutputPort {
    CreatedFornecedorModel present(final Integer id);
    void cnpjIsInvalid() throws BusinessRuleException;
    void fornecedorAlreadyExists() throws BusinessRuleException;
}
