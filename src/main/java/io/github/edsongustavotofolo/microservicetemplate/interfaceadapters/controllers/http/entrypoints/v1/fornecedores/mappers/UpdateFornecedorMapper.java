package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateFornecedorMapper {

    UpdateFornecedorMapper INSTANCE = Mappers.getMapper(UpdateFornecedorMapper.class);

    @Mapping(target = "logradouro", source = "updateFornecedorRequest.endereco.logradouro")
    @Mapping(target = "numero", source = "updateFornecedorRequest.endereco.numero")
    @Mapping(target = "bairro", source = "updateFornecedorRequest.endereco.bairro")
    @Mapping(target = "complemento", source = "updateFornecedorRequest.endereco.complemento")
    @Mapping(target = "pontoDeReferencia", source = "updateFornecedorRequest.endereco.pontoDeReferencia")
    @Mapping(target = "cep", source = "updateFornecedorRequest.endereco.cep")
    @Mapping(target = "cidade", source = "updateFornecedorRequest.endereco.cidade")
    UpdateFornecedor toModel(final Integer id, final UpdateFornecedorRequest updateFornecedorRequest);
}
