package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;

import java.util.List;

public class CreateFornecedorRequestFixture {

    private CreateFornecedorRequestFixture() {}

    public static CreateFornecedorRequest umFornecedorRequest() {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest umFornecedorRequestComCnpj(final String cnpj) {
        return CreateFornecedorRequest.builder()
                .cnpj(cnpj)
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }
}