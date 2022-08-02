package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

public interface FornecedorCriadoOutputBoundary {
    NovoFornecedorResponseModel present(final Integer id);
}
