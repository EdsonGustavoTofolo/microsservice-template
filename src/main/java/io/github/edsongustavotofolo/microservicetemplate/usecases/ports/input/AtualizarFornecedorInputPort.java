package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

@FunctionalInterface
public interface AtualizarFornecedorInputPort {
    void execute(final Integer id, final UpdateFornecedor requestModel) throws BusinessRuleException;
}
