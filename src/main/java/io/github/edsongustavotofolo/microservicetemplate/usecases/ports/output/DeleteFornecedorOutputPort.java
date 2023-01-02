package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface DeleteFornecedorOutputPort {
    void fornecedorNaoEncontrado() throws BusinessRuleException;
}
