package io.github.edsongustavotofolo.microservicetemplate.usecases.providers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;

import java.util.Optional;

public interface FornecedorProvider {
    Integer create(final Fornecedor fornecedor);

    boolean existsFornecedorWithCnpj(final String cnpj);

    Optional<Fornecedor> getById(final Integer id);

    void update(final Fornecedor fornecedor);
}
