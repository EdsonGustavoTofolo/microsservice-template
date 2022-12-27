package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;

public final class CreateContatoRequestFixture {

    private CreateContatoRequestFixture() {}

    public static CreateContatoRequest createContatoRequestComTipoDeContato(final TipoDeContatoEnum value) {
        return CreateContatoRequest.builder()
                .tipoDeContato(value)
                .build();
    }
}
