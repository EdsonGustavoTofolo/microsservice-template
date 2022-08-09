package io.github.edsongustavotofolo.microservicetemplate.usecases.gateways;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;

import java.util.Optional;

public interface FornecedorDsGateway {
    Integer criar(final Fornecedor fornecedor);

    boolean existeFornecedorComCnpj(final String cnpj);

    Optional<Fornecedor> buscarPorId(final Integer id);

    void atualizar(final Fornecedor fornecedor);
}
