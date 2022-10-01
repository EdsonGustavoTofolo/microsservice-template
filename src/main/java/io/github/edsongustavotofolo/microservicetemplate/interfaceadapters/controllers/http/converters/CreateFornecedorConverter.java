package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.converters;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.CreateFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedorModel;

public class CreateFornecedorConverter {

    private CreateFornecedorConverter() {
        throw new IllegalStateException("Unity class");
    }

    public static CreateFornecedorModel toModel(final CreateFornecedorRequestModel requestModel) {
        return CreateFornecedorModel.builder()
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
                .contatos(
                        requestModel.getContatos().stream()
                                .map(contatoRequestModel ->
                                    switch (contatoRequestModel.getTipoDeContato()) {
                                        case EMAIL:
                                            yield CreateContatoModel.email(contatoRequestModel.getEnderecoEmail());
                                        case TELEFONE:
                                            yield CreateContatoModel.telefone(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                        case CELULAR:
                                            yield CreateContatoModel.celular(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                        case SITE:
                                            yield CreateContatoModel.site(contatoRequestModel.getUrlSite());
                                        case OUTRO:
                                            yield CreateContatoModel.outro(contatoRequestModel.getTexto());
                                    }
                                ).toList()
                )
                .build();
    }
}
