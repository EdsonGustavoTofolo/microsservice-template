package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Pais;

public final class PaisImpl implements Pais {
    private final Integer id;
    private final String nome;

    public PaisImpl(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }
}
