package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;

public final class CreateFornecedorMapper {

    private CreateFornecedorMapper() {}

    public static CreateFornecedor toModel(final CreateFornecedorRequest requestModel) {
        return CreateFornecedor.builder()
                .cnpj(requestModel.getCnpj())
                .razaoSocial(requestModel.getRazaoSocial())
                .nomeFantasia(requestModel.getNomeFantasia())
                .observacao(requestModel.getObservacao())
                .logradouro(requestModel.getLogradouro())
                .numero(requestModel.getNumero())
                .bairro(requestModel.getBairro())
                .complemento(requestModel.getComplemento())
                .pontoDeReferencia(requestModel.getPontoDeReferencia())
                .cep(requestModel.getCep())
                .cidadeId(requestModel.getCidade())
                .observacaoContatos(requestModel.getObservacaoContatos())
                .contatos(
                        requestModel.getContatos().stream()
                                .map(contatoRequestModel ->
                                        switch (TipoDeContatoEnum.valueOf(contatoRequestModel.getTipoDeContato())) {
                                            case EMAIL:
                                                yield CreateContato.email(contatoRequestModel.getEnderecoEmail());
                                            case TELEFONE:
                                                yield CreateContato.telefone(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                            case CELULAR:
                                                yield CreateContato.celular(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                            case SITE:
                                                yield CreateContato.site(contatoRequestModel.getUrlSite());
                                            case OUTRO:
                                                yield CreateContato.outro(contatoRequestModel.getTexto());
                                        }
                                ).toList()
                )
                .build();
    }
}
