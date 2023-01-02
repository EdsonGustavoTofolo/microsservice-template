package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.ContatosBuilder.umContatos;
import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.EnderecoBuilder.umEndereco;

public final class FornecedorBuilder {
    private Fornecedor fornecedor;

    private FornecedorBuilder() {}

    public static FornecedorBuilder umFornecedor() {
        final var fornecedorImpl = new FornecedorImpl(
                1,
                new Cnpj("45135006000104"),
                "Fornecedor Ltda",
                "Fornecedor & Cia",
                "nenhuma obs",
                umEndereco().build(),
                umContatos().build());

        final var builder = new FornecedorBuilder();
        builder.fornecedor = fornecedorImpl;
        return builder;
    }

    public static FornecedorBuilder umFornecedorSemId() {
        final var fornecedorImpl = new FornecedorImpl(
                null,
                new Cnpj("45135006000104"),
                "Fornecedor Ltda",
                "Fornecedor & Cia",
                "nenhuma obs",
                umEndereco().build(),
                umContatos().build());

        final var builder = new FornecedorBuilder();
        builder.fornecedor = fornecedorImpl;
        return builder;
    }

    public Fornecedor build() {
        return this.fornecedor;
    }
}
