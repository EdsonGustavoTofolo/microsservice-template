package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EstadoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.PaisImpl;

public final class CidadeBuilder {

    private Cidade cidade;

    private CidadeBuilder() {}

    public static CidadeBuilder umaCidade() {
        final var builder = new CidadeBuilder();

        builder.cidade = new CidadeImpl(
                1,
                "Chapecó",
                new EstadoImpl(
                        1,
                        "Santa Catarina",
                        "SC",
                        new PaisImpl(1, "Brasil")
                )
        );

        return builder;
    }

    public Cidade build() {
        return this.cidade;
    }
}
