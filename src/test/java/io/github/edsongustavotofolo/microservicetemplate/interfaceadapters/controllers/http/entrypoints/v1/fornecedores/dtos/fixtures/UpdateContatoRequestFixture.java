package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateContatoRequest;

public final class UpdateContatoRequestFixture {

    private UpdateContatoRequestFixture() {}

    public static UpdateContatoRequest updateContatoRequestComTipoDeContato(final String value) {
        return UpdateContatoRequest.builder()
                .tipoDeContato(value)
                .build();
    }
}
