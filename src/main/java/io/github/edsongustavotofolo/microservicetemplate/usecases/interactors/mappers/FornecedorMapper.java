package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContatoMapper.class})
public interface FornecedorMapper {

    FornecedorMapper INSTANCE = Mappers.getMapper(FornecedorMapper.class);

    default Fornecedor map(final CreateFornecedor createFornecedor) {
        return toDomain(createFornecedor);
    }

    Cnpj map(final String numero);

    FornecedorImpl toDomain(final CreateFornecedor createFornecedor);
}
