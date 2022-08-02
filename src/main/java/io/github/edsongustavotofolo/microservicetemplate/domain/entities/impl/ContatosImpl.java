package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Contato;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatosImpl implements Contatos {
    private Integer id;
    @Setter
    private String observacao;
    private List<Contato> lista;

    public ContatosImpl() {
        this.lista = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getObservacao() {
        return observacao;
    }

    @Override
    public List<Contato> getLista() {
        return Collections.unmodifiableList(lista);
    }

    @Override
    public void adicionar(final Contato contato) {
        lista.add(contato);
    }
}
