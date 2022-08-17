package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

@FunctionalInterface
public interface CriarFornecedorInputBoundary {
    NovoFornecedorResponseModel execute(final CreateFornecedorModel fornecedorRequestModel);
}
