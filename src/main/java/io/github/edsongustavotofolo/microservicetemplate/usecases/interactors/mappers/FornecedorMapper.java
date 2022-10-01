package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateFornecedorModel;

public interface FornecedorMapper {
    Fornecedor toDomain(final CreateFornecedorModel fornecedorRequestModel);
}
