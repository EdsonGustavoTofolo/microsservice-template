package io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorRequestModel;

public interface FornecedorMapper {
    Fornecedor toDomain(final CreateFornecedorRequestModel fornecedorRequestModel);
}
