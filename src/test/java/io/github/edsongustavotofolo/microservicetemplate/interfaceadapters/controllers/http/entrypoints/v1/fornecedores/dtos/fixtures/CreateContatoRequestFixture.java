package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;

public final class CreateContatoRequestFixture {

    private CreateContatoRequestFixture() {}

    public static CreateContatoRequest createContatoRequestComTipoDeContato(final String value) {
        return CreateContatoRequest.builder()
                .tipoDeContato(value)
                .build();
    }
}
