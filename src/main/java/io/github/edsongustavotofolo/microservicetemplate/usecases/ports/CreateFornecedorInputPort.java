package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreatedFornecedorModel;

@FunctionalInterface
public interface CreateFornecedorInputPort {
    CreatedFornecedorModel execute(final CreateFornecedorModel fornecedorRequestModel);
}
