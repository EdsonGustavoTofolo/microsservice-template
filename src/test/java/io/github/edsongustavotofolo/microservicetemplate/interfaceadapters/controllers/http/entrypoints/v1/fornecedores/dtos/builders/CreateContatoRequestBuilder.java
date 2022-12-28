package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;

public final class CreateContatoRequestBuilder {

    private CreateContatoRequest.CreateContatoRequestBuilder request;

    private CreateContatoRequestBuilder() {}

    public static CreateContatoRequest.CreateContatoRequestBuilder createContatoRequest() {
        final var builder = new CreateContatoRequestBuilder();
        builder.request = CreateContatoRequest.builder();
        return builder.request;
    }
}
