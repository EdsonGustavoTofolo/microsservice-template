package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;

public class EnderecoImpl implements Endereco {
    private final Integer id;
    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final String complemento;
    private final String pontoDeReferencia;
    private final Cep cep;
    private final Cidade cidade;

    public EnderecoImpl(String logradouro, String numero, String bairro, String complemento, String pontoDeReferencia, Cep cep, Cidade cidade) {
        this.id = null;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.pontoDeReferencia = pontoDeReferencia;
        this.cep = cep;
        this.cidade = cidade;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getLogradouro() {
        return logradouro;
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public String getBairro() {
        return bairro;
    }

    @Override
    public String getComplemento() {
        return complemento;
    }

    @Override
    public String getPontoDeReferencia() {
        return pontoDeReferencia;
    }

    @Override
    public Cep getCep() {
        return cep;
    }

    @Override
    public Cidade getCidade() {
        return cidade;
    }
}
