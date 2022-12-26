package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateFornecedorMapper {

    UpdateFornecedorMapper INSTANCE = Mappers.getMapper(UpdateFornecedorMapper.class);

    UpdateFornecedor toModel(final UpdateFornecedorRequest updateFornecedorRequest);
}
