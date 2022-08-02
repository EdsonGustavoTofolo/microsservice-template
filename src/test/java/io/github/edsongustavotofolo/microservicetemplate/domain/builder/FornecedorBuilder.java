package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.ContatosBuilder.umContatos;
import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.EnderecoBuilder.umEndereco;

public class FornecedorBuilder {
    private Fornecedor fornecedor;

    private FornecedorBuilder() {}

    public static FornecedorBuilder umFornecedor() {
        Fornecedor fornecedorImpl = new FornecedorImpl(
                new Cnpj("45135006000104"),
                "Fornecedor Ltda",
                "Fornecedor & Cia",
                "nenhuma obs",
                umEndereco().get(),
                umContatos().get());

        var builder = new FornecedorBuilder();
        builder.fornecedor = fornecedorImpl;
        return builder;
    }

    public Fornecedor get() {
        return this.fornecedor;
    }
}
