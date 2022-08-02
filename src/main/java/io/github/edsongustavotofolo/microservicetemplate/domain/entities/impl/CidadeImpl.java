package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Estado;

public final class CidadeImpl implements Cidade {
    private final Integer id;
    private final String nome;
    private final Estado estado;

    public CidadeImpl(Integer id) {
        this.id = id;
        this.nome = "";
        this.estado = null;
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
    public Estado getEstado() {
        return estado;
    }
}
