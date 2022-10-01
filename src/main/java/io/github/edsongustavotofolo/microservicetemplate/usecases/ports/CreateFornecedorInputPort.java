package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreatedFornecedorModel;

@FunctionalInterface
public interface CreateFornecedorInputPort {
    CreatedFornecedorModel execute(final CreateFornecedorModel fornecedorRequestModel);
}
