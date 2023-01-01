package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface UpdateFornecedorOutputPort {
    BusinessRuleException fornecedorNaoEncontrado();
}
