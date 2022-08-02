package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

@FunctionalInterface
public interface CriarFornecedorInputBoundary {
    NovoFornecedorResponseModel execute(final CriarFornecedorRequestModel criarFornecedorRequestModel);
}
