package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;

import java.util.List;

import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.CreateEnderecoRequestBuilder.createEnderecoRequest;

public final class CreateFornecedorRequestBuilder {

    private CreateFornecedorRequest.CreateFornecedorRequestBuilder request;

    private CreateFornecedorRequestBuilder() {
    }

    public static CreateFornecedorRequest.CreateFornecedorRequestBuilder createFornecedorRequest() {
        final var builder = new CreateFornecedorRequestBuilder();

        builder.request = CreateFornecedorRequest.builder();
        builder.request.cnpj("68278677000120");
        builder.request.razaoSocial("Razao Social");
        builder.request.nomeFantasia("Nome Fantasia");
        builder.request.observacao("Observacao");
        builder.request.endereco(createEnderecoRequest().build());
        builder.request.contatos(List.of(
                CreateContatoRequest.outro("0800 123 123 123"),
                CreateContatoRequest.site("https://dns.com.br/mysite")));
        builder.request.observacaoContatos("meus contatos");

        return builder.request;
    }
}
