package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface CreateFornecedorOutputPort<T> {
    void show(final Integer id);

    T get();

    void cnpjIsInvalid() throws BusinessRuleException;

    void fornecedorAlreadyExists() throws BusinessRuleException;
}
