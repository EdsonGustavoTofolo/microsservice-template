package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.mappers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;

public class CreateFornecedorMapper {

    private CreateFornecedorMapper() {
        throw new IllegalStateException("Unity class");
    }

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
                .contatos(
                        requestModel.getContatos().stream()
                                .map(contatoRequestModel ->
                                        switch (contatoRequestModel.getTipoDeContato()) {
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
