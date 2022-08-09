package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;

public class FornecedorImpl implements Fornecedor {
    private Integer id;
    private Cnpj cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String observacao;
    private Endereco endereco;
    private Contatos contatos;

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

    @Override
    public void setCnpj(final Cnpj cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void setRazaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public void setNomeFantasia(final String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    @Override
    public void setEndereco(final String logradouro, final String numero, final String bairro, final String complemento,
                            final String pontoDeReferencia, final String cep, final Integer cidadeId) {
        this.endereco = new EnderecoImpl(
                this.endereco.getId(),
                logradouro,
                numero,
                bairro,
                complemento,
                pontoDeReferencia,
                new Cep(cep),
                new CidadeImpl(cidadeId));
    }
}
