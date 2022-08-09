package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.UpdateFornecedorRequestModel;

@FunctionalInterface
public interface AtualizarFornecedorInputBoundary {
    void execute(final Integer id, final UpdateFornecedorRequestModel requestModel);
}
