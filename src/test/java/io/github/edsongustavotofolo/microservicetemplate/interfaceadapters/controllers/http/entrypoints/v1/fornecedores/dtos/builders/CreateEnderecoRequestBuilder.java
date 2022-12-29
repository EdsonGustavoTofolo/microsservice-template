package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateEnderecoRequest;

public final class CreateEnderecoRequestBuilder {

    private CreateEnderecoRequest.CreateEnderecoRequestBuilder request;

    private CreateEnderecoRequestBuilder() {}

    public static CreateEnderecoRequest.CreateEnderecoRequestBuilder createEnderecoRequest() {
        final var builder = new CreateEnderecoRequestBuilder();

        builder.request = CreateEnderecoRequest.builder();

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
