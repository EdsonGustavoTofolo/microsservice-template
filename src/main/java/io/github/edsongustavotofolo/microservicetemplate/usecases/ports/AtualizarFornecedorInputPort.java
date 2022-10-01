package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.UpdateFornecedorModel;

@FunctionalInterface
public interface AtualizarFornecedorInputPort {
    void execute(final Integer id, final UpdateFornecedorModel requestModel);
}