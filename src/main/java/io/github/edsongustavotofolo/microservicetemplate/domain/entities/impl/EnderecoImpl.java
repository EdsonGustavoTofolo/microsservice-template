package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;

public class EnderecoImpl implements Endereco {
    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String pontoDeReferencia;
    private Cep cep;
    private Cidade cidade;

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

    public EnderecoImpl(Integer id, String logradouro, String numero, String bairro, String complemento, String pontoDeReferencia, Cep cep, Cidade cidade) {
        this(logradouro, numero, bairro, complemento, pontoDeReferencia, cep, cidade);
        this.id = id;
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
