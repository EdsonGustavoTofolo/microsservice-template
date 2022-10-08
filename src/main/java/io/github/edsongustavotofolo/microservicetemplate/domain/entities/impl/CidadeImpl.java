package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Estado;

public final class CidadeImpl implements Cidade {
    private final Integer id;
    private final String nome;
    private final Estado estado;

    public CidadeImpl(final Integer id) {
        this.id = id;
        this.nome = "";
        this.estado = null;
    }

    public CidadeImpl(final Integer id, final String nome, final Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public Estado getEstado() {
        return this.estado;
    }
}
