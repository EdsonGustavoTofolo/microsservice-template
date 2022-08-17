package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;

public interface FornecedorAtualizadoOutputBoundary {
    Fornecedor fornecedorNaoEncontrado() throws BusinessRuleException;
}
