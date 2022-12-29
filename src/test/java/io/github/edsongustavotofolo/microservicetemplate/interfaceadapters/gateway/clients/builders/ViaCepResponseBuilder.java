package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateway.clients.builders;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients.ViaCepClient;

public final class ViaCepResponseBuilder {

    private ViaCepClient.ViaCepResponse response;

    private ViaCepResponseBuilder() {}

    public static ViaCepResponseBuilder umViaCepResponse() {
        final var builder = new ViaCepResponseBuilder();

        builder.response = new ViaCepClient.ViaCepResponse(
                "89803000",
                "Novo Logradouro",
                "sem complemento",
                "Centro",
                "Chapecó",
                "SC",
                "1234",
                false
        );

        return builder;
    }

    public ViaCepClient.ViaCepResponse build() {
        return this.response;
    }
}
