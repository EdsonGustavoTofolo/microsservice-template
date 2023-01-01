package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.update;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update.UpdateEnderecoRequest;

public final class UpdateEnderecoRequestBuilder {

    private UpdateEnderecoRequest.UpdateEnderecoRequestBuilder request;

    private UpdateEnderecoRequestBuilder() {}

    public static UpdateEnderecoRequest.UpdateEnderecoRequestBuilder updateEnderecoRequest() {
        final var builder = new UpdateEnderecoRequestBuilder();

        builder.request = UpdateEnderecoRequest.builder();

        builder.request.logradouro("Rua Washington Luis");
        builder.request.numero("210");
        builder.request.bairro("São Cristóvão");
        builder.request.complemento("E");
        builder.request.pontoDeReferencia("Edificio XPTO, apto 1000");
        builder.request.cep("89803000");
        builder.request.cidade(151515);
        builder.request.uf("SC");

        return builder.request;
    }

}
