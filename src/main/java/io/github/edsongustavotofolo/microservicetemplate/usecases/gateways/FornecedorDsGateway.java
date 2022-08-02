package io.github.edsongustavotofolo.microservicetemplate.usecases.gateways;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;

public interface FornecedorDsGateway {
    Integer criar(final Fornecedor fornecedor);
}
