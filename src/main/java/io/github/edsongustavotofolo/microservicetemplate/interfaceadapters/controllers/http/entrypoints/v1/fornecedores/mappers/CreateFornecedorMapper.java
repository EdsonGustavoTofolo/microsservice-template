package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateFornecedorMapper {

    CreateFornecedorMapper INSTANCE = Mappers.getMapper(CreateFornecedorMapper.class);

    @Mapping(target = "logradouro", source = "endereco.logradouro")
    @Mapping(target = "numero", source = "endereco.numero")
    @Mapping(target = "bairro", source = "endereco.bairro")
    @Mapping(target = "complemento", source = "endereco.complemento")
    @Mapping(target = "pontoDeReferencia", source = "endereco.pontoDeReferencia")
    @Mapping(target = "cep", source = "endereco.cep")
    @Mapping(target = "cidade", source = "endereco.cidade")
    CreateFornecedor toModel(final CreateFornecedorRequest requestModel);
}
