package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

@FunctionalInterface
public interface CriarFornecedorInputBoundary {
    NovoFornecedorResponseModel execute(final CreateFornecedorRequestModel fornecedorRequestModel);
}
