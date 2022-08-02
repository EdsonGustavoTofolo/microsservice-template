package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Estado;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Pais;

public class EstadoImpl implements Estado {
    private final Integer id;
    private final String nome;
    private final String sigla;
    private final Pais pais;

    public EstadoImpl(Integer id, String nome, String sigla, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getSigla() {
        return sigla;
    }

    @Override
    public Pais getPais() {
        return pais;
    }
}
