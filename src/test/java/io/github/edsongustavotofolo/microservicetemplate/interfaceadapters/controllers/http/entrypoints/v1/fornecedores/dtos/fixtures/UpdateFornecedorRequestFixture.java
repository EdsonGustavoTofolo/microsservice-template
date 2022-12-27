package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;

import java.util.List;

public final class UpdateFornecedorRequestFixture {

    private UpdateFornecedorRequestFixture() {}

    public static UpdateFornecedorRequest updateFornecedorRequest() {
        return UpdateFornecedorRequest.builder()
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
                        UpdateContatoRequest.outro(1, "0800 123 123 123"),
                        UpdateContatoRequest.site(2, "https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }
}
