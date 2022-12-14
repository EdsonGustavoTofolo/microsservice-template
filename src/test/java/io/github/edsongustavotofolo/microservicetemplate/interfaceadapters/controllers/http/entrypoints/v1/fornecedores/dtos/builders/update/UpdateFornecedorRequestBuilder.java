package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.update;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update.UpdateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update.UpdateFornecedorRequest;

import java.util.List;

import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.update.UpdateEnderecoRequestBuilder.updateEnderecoRequest;

public final class UpdateFornecedorRequestBuilder {

    private UpdateFornecedorRequest.UpdateFornecedorRequestBuilder request;

    private UpdateFornecedorRequestBuilder() {
    }

    public static UpdateFornecedorRequest.UpdateFornecedorRequestBuilder updateFornecedorRequest() {
        final var builder = new UpdateFornecedorRequestBuilder();

        builder.request = UpdateFornecedorRequest.builder();

        builder.request.razaoSocial("Razao Social");
        builder.request.nomeFantasia("Nome Fantasia");
        builder.request.observacao("Observacao");
        builder.request.endereco(updateEnderecoRequest().build());
        builder.request.contatos(List.of(
                UpdateContatoRequest.outro(1, "0800 123 123 123"),
                UpdateContatoRequest.site(2, "https://dns.com.br/mysite")));
        builder.request.observacaoContatos("meus contatos");

        return builder.request;
    }
}
