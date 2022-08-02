package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;

public final class FornecedorImpl implements Fornecedor {
    private final Integer id;
    private final Cnpj cnpj;
    private final String razaoSocial;
    private final String nomeFantasia;
    private final String observacao;
    private final Endereco endereco;
    private final Contatos contatos;

    public FornecedorImpl(Cnpj cnpj, String razaoSocial, String nomeFantasia, String observacao, Endereco endereco, Contatos contatos) {
        this.id = null;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.observacao = observacao;
        this.endereco = endereco;
        this.contatos = contatos;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Cnpj getCnpj() {
        return cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return razaoSocial;
    }

    @Override
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    @Override
    public String getObservacao() {
        return observacao;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public Contatos getContatos() {
        return contatos;
    }
}
