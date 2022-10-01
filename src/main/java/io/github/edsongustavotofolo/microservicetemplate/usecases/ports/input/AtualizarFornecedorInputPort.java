package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedorModel;

@FunctionalInterface
public interface AtualizarFornecedorInputPort {
    void execute(final Integer id, final UpdateFornecedorModel requestModel);
}
