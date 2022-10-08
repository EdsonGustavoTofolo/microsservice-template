package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateFornecedorMapper {

    UpdateFornecedorMapper INSTANCE = Mappers.getMapper(UpdateFornecedorMapper.class);

    UpdateFornecedor toModel(final UpdateFornecedorRequest updateFornecedorRequest);
}
