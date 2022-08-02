package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EnderecoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;

public class EnderecoBuilder {
    private Endereco endereco;

    private EnderecoBuilder() {}

    public static EnderecoBuilder umEndereco() {
        var builder = new EnderecoBuilder();
        builder.endereco = new EnderecoImpl(
                "Rua Sao Miguel do Oeste",
                "1111",
                "Jardim das Americas",
                "apto 701, bloco A",
                "prox a Farmacia",
                new Cep("85601000"),
                new CidadeImpl(4115200));
        return builder;
    }

    public Endereco get() {
        return this.endereco;
    }
}
